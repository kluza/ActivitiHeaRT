package newheartconnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import bracketparser.CompositeElement;
import bracketparser.Element;

/** @author ja */
public class HeartRequestHandler {
    public String inferenceRequest(String userName, String modelName, String method, String[] tables,
            String state) {
        StringBuilder tabSB = new StringBuilder();
        tabSB.append('[');
        for (String t: tables) {
            tabSB.append(t + ',');
        }
        tabSB.deleteCharAt(tabSB.length() - 1);
        tabSB.append(']');
        return "[model,run," + modelName + "," + userName + "," + method + "," + tabSB.toString() + ","
                + state + "].";
    }
    
    public Map<String, Object> parseResponse(String response) {
        Map<String, Object> result = new HashMap<String, Object>();
        CompositeElement retState =
                (CompositeElement) ((CompositeElement) Element.parse(response)).getSubs().get(1);
        for (Element e: retState.getSubs()) {
            List<Element> subs = ((CompositeElement) e).getSubs();
            result.put(subs.get(0).toString(), subs.get(1).toString());
        }
        return result;
    }
    
    public String makeStateDef(Map<String, Object> state) {
        StringBuilder stateSB = new StringBuilder();
        stateSB.append('[');
        for (String attr: state.keySet()) {
            stateSB.append('[' + attr + ',');
            stateSB.append(state.get(attr).toString() + "],");
        }
        stateSB.deleteCharAt(stateSB.length() - 1);
        stateSB.append(']');
        return stateSB.toString();
    }
    
    public boolean isSuccess(String response) {
        CompositeElement resTree = (CompositeElement) Element.parse(response);
        String status = resTree.getSubs().get(0).toString();
        return (status.equals("true"));
    }
    
    private List<String> getAtts(String response, boolean in) {
        CompositeElement expression = (CompositeElement) Element.parse(response);
        CompositeElement allAtts = (CompositeElement) expression.getSubs().get(1);
        CompositeElement inAtts = (CompositeElement) allAtts.getSubs().get(0);
        CompositeElement outAtts = (CompositeElement) allAtts.getSubs().get(1);
        CompositeElement foo;
        foo = in ? inAtts : outAtts;
        List<String> result = new ArrayList<String>();
        for (Element e: foo.getSubs()) {
            result.add(e.toString());
        }
        return result;
    }
    
    public List<String> getInAtts(String response) {
        return getAtts(response, true);
    }
    
    public List<String> getOutAtts(String response) {
        return getAtts(response, false);
    }
    
    public String schemeRequest(String modelName, String userName, String table) {
        return "[scheme,get," + modelName + "," + userName + "," + table + "].";
    }
}

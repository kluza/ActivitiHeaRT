package pl.edu.agh.heart.taskdelegate;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import pl.edu.agh.heart.comm.HeartRequestHandler;
import pl.edu.agh.heart.comm.HttpConnector;
import pl.edu.agh.heart.constants.Constants;

/** @author ja */
public class HeartDelegate implements JavaDelegate {
    private Expression modelName;
    private Expression userName;
    private Expression table;
    private Expression attributeSet;
    private HeartRequestHandler heartHandler = new HeartRequestHandler();
    private HttpConnector httpConnector;
    
    public HeartDelegate() throws IOException {
        Properties props = new Properties();
        InputStream propIs = getClass().getClassLoader().getResourceAsStream(Constants.H_CONN_PATH);
        props.load(propIs);
        String host = props.getProperty("hostName");
        int port = Integer.valueOf(props.getProperty("port"));
        httpConnector = new HttpConnector(true, host, port);
    }
    
    public void execute(DelegateExecution execution) throws Exception {
        String modelNameString = (String) modelName.getValue(execution);
        String userNameString = (String) userName.getValue(execution);
        String[] tableString = {(String) table.getValue(execution)};
        Map<String, Object> stateMap =
                makeStateMap(modelNameString, userNameString, tableString[0], execution);
        String stateDef = heartHandler.makeStateDef(stateMap);
        String request =
                heartHandler.inferenceRequest(userNameString, modelNameString, "foi", tableString, stateDef);
        
        String response = httpConnector.performRequest(request);
        if (!heartHandler.isSuccess(response)) {
            throw new Exception("Heart request failed!");
        }
        Map<String, Object> result = heartHandler.parseInferenceResponse(response);
        saveState(result, execution);
    }
    
    private Map<String, Object> makeStateMap(String modelName, String userName, String table,
            DelegateExecution execution) throws Exception {
        Map<String, Object> stateMap = new HashMap<String, Object>();
        String schemeRequest = heartHandler.schemeRequest(modelName, userName, table);
        String response = httpConnector.performRequest(schemeRequest);
        if (!heartHandler.isSuccess(response)) {
            throw new Exception("Heart request failed!");
        }
        List<String> inAtts = heartHandler.parseInAttributes(response);
        for (String att: inAtts) {
            if (!execution.hasVariable(att)) {
                throw new ActivitiException("Variable " + att + " has not been defined!");
            }
            stateMap.put(att, execution.getVariable(att));
        }
        return stateMap;
    }
    
    private void saveState(Map<String, Object> state, DelegateExecution execution) {
        for (String s: state.keySet()) {
            execution.setVariable(s, state.get(s));
        }
    }
    
    public Expression getModelName() {
        return modelName;
    }
    
    public void setModelName(Expression modelName) {
        this.modelName = modelName;
    }
    
    public Expression getUserName() {
        return userName;
    }
    
    public void setUserName(Expression userName) {
        this.userName = userName;
    }
    
    public Expression getTable() {
        return table;
    }
    
    public void setTable(Expression table) {
        this.table = table;
    }
    
    public Expression getAttributeSet() {
        return attributeSet;
    }
    
    public void setAttributeSet(Expression attributeSet) {
        this.attributeSet = attributeSet;
    }
    
}

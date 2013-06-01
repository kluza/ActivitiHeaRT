/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import pl.edu.agh.heart.bracketparser.CompositeElement;
import pl.edu.agh.heart.bracketparser.Element;
import pl.edu.agh.heart.constants.Constants;

/** @author ja */
public class HeartRepository {
    private HttpConnector httpConnector;
    
    public HeartRepository() throws IOException {
        Properties props = new Properties();
        InputStream propIs = getClass().getClassLoader().getResourceAsStream(Constants.H_CONN_PATH);
        props.load(propIs);
        String host = props.getProperty("hostName");
        int port = Integer.valueOf(props.getProperty("port"));
        httpConnector = new HttpConnector(true, host, port);
    }
    
    public Map<String, List<String>> getModelNames() throws Exception {
        String modelListRequest = "[model,getlist].";
        String response = httpConnector.performRequest(modelListRequest);
        CompositeElement responseTree = (CompositeElement) Element.parse(response);
        List<Element> modelList = ((CompositeElement) responseTree.getSubs().get(1)).getSubs();
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        for (Element e: modelList) {
            String model = ((CompositeElement) e).getSubs().get(0).toString();
            String user = ((CompositeElement) e).getSubs().get(1).toString();
            List<String> l = result.get(user);
            if (l == null) {
                l = new ArrayList<String>();
                result.put(user, l);
            }
            l.add(model);
        }
        return result;
    }
}

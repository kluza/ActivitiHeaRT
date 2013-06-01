/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.explorer;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import pl.edu.agh.heart.comm.HeartRepository;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Tree;

/** @author ja */
public class ModelTree {
    public static Tree get(HeartPanel panel) {
        Tree tree = new Tree();
        tree.setImmediate(true);
        try {
            HeartRepository hr = new HeartRepository();
            Map<String, List<String>> modelMap = hr.getModelNames();
            for (String user: modelMap.keySet()) {
                tree.addItem(user);
                List<String> l = modelMap.get(user);
                if ((l == null) || l.isEmpty()) {
                    tree.setChildrenAllowed(user, false);
                } else {
                    for (String model: l) {
                        Model m = new Model(user, model);
                        tree.addItem(m);
                        tree.setChildrenAllowed(m, false);
                        tree.setParent(m, user);
                    }
                }
                tree.expandItemsRecursively(user);
            }
            tree.addListener(new ModelTreeListener(tree, panel));
        } catch (IOException exception) {
            String errMsg = "Couldn't connect";
            tree.addItem(errMsg);
            tree.setChildrenAllowed(errMsg, false);
        } catch (Exception exception) {
            String errMsg = "Something's fucked up";
            tree.addItem(errMsg);
            tree.setChildrenAllowed(errMsg, false);
        }
        return tree;
    }
    
    static class Model {
        public String user, name;
        
        public Model(String user, String name) {
            this.user = user;
            this.name = name;
        }
        
        public String toString() {
            return name;
        }
    }
}


class ModelTreeListener implements ValueChangeListener {
    private Tree tree;
    private HeartPanel panel;
    
    public ModelTreeListener(Tree tree, HeartPanel panel) {
        this.tree = tree;
        this.panel = panel;
    }
    
    public void valueChange(ValueChangeEvent pEvent) {
        Object value = tree.getValue();
        if (value instanceof ModelTree.Model) {
            panel.getText().setValue(
                    "" + ((ModelTree.Model) value).user + "/" + ((ModelTree.Model) value).name);
        }
    }
    
}

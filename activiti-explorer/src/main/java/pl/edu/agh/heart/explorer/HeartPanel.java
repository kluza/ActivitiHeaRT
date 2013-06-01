/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.explorer;

import org.activiti.explorer.ui.custom.DetailPanel;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Tree;

/** @author ja */
public class HeartPanel extends DetailPanel {
    private Tree modelTree;
    private TextArea text = new TextArea();
    
    public void attach() {
        super.attach();
        HorizontalLayout layout = new HorizontalLayout();
        modelTree = ModelTree.get(this);
        layout.addComponent(modelTree);
        layout.addComponent(text);
        addDetailComponent(layout);
    }
    
    TextArea getText() {
        return text;
    }
}

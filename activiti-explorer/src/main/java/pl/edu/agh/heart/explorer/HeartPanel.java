/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.explorer;

import org.activiti.explorer.ui.custom.DetailPanel;
import com.vaadin.ui.Label;

/** @author ja */
public class HeartPanel extends DetailPanel {
    public HeartPanel() {
        addDetailComponent(new Label("Hello world"));
    }
}

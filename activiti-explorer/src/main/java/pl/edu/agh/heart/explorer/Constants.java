/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.explorer;

import org.activiti.explorer.ExplorerApp;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/** @author ja */
public class Constants {
    public static final String HEART = "HeaRT";
    
    public static ClickListener getClickListener() {
        return new ClickListener() {
            public void buttonClick(ClickEvent pEvent) {
                ExplorerApp.get().getViewManager().showHeartView();
            }
        };
    }
}

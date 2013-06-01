/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.explorer;

import org.activiti.explorer.ui.AbstractPage;
import org.activiti.explorer.ui.custom.ToolBar;
import com.vaadin.ui.AbstractSelect;

/** @author ja */
public class HeartPage extends AbstractPage {
    
    protected ToolBar createMenuBar() {
        return null;
    }
    
    protected AbstractSelect createSelectComponent() {
        return null;
    }
    
    public void refreshSelectNext() {
    }
    
    public void selectElement(int pIndex) {
    }
    
    @Override
    public void initUi() {
        super.initUi();
        setDetailComponent(new HeartPanel());
    }
    
}

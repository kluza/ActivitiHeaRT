/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package bracketparser;

import java.util.ArrayList;
import java.util.List;

/** @author Comarch */
public class CompositeElement extends Element {
    private List<Element> subs = new ArrayList<Element>();
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (Element e: subs) {
            sb.append(e.toString() + ',');
        }
        if (sb.length() >= 2) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(']');
        return sb.toString();
    }
    
    public void print() {
        System.out.println("Composite element. subs: ");
        for (Element sub: subs) {
            sub.print();
        }
        System.out.println("end composite element");
    }
    
    public boolean isComposite() {
        return true;
    }
    
    public void addSub(Element e) {
        subs.add(e);
    }
    
    public List<Element> getSubs() {
        return subs;
    }
}

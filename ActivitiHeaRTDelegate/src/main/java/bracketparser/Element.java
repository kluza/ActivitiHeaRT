/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package bracketparser;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/** @author Comarch */
public abstract class Element {
    public CompositeElement master;
    
    public abstract String toString();
    
    public abstract boolean isComposite();
    
    public abstract void print();
    
    private static List<Character> specialSigns = Arrays.asList('[', ',', ']');
    
    public static Element parse(String expression) {
        if (expression.charAt(0) != '[') {
            return new SimpleElement(expression);
        }
        Stack<CompositeElement> elStack = new Stack<CompositeElement>();
        CompositeElement currElem = null;
        StringBuilder currString = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '[') {
                elStack.push(new CompositeElement());
            }
            if (specialSigns.contains(c) && (currString.length() != 0)) {
                new SimpleElement(currString.toString()).setMaster(elStack.peek());
                currString = new StringBuilder();
            }
            if (c == ']') {
                currElem = elStack.pop();
                if (!elStack.isEmpty()) {
                    currElem.setMaster(elStack.peek());
                }
            }
            if (!specialSigns.contains(c)) {
                currString.append(c);
            }
        }
        return currElem;
    }
    
    public void setMaster(CompositeElement master) {
        if (master != null) {
            master.addSub(this);
        }
        this.master = master;
    }
}

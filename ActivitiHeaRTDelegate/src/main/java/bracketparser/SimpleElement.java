/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package bracketparser;

/** @author Comarch */
public class SimpleElement extends Element {
    private String value;
    
    public void print() {
        System.out.println("Simple element. value: " + value);
    }
    
    public SimpleElement(String value) {
        this.value = value;
    }
    
    public String toString() {
        return value;
    }
    
    public boolean isComposite() {
        return false;
    }
    
}

/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package protocol;

import java.util.List;
import java.util.Map;
import org.junit.Test;
import pl.edu.agh.heart.comm.HeartRepository;

/** @author ja */
public class ProtocolTest {
    
    @Test
    public void test() throws Exception {
        HeartRepository hr = new HeartRepository();
        Map<String, List<String>> dupa = hr.getModelNames();
        for (String user: dupa.keySet()) {
            System.out.println(user + ':' + dupa.get(user));
        }
    }
}

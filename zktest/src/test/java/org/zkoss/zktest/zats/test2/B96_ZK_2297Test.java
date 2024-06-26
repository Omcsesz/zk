/* B96_ZK_2297Test.java

	Purpose:
		
	Description:
		
	History:
		Fri Apr 14 15:52:31 CST 2023, Created by jameschu

Copyright (C) 2023 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zktest.zats.ZATSTestCase;

/**
 * @author jameschu
 */

public class B96_ZK_2297Test extends ZATSTestCase {
	@Test
	public void test() throws Exception {
		DesktopAgent desktopAgent = connect();
		List<ComponentAgent> button = desktopAgent.queryAll("button");
		for (int i = 0; i < 4; i++) {
			button.get(i).click();
		}
		List<String> zkLog = desktopAgent.getZkLog();
		assertTrue(zkLog.get(0).startsWith("[MouseEvent onClick"));
		assertEquals("onClick", zkLog.get(1));
		assertTrue(zkLog.get(2).startsWith("[MouseEvent onClick"));
		assertEquals("onClick", zkLog.get(3));
	}
}

/* F02545ChildrenBindingMapTest.java
	Purpose:

	Description:

	History:
		Fri Apr 30 18:12:24 CST 2021, Created by jameschu

Copyright (C) 2021 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.bind.issue;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zktest.zats.ZATSTestCase;
import org.zkoss.zul.Label;

/**
 * @author jameschu
 */
public class F02545ChildrenBindingMapTest extends ZATSTestCase {
	@Test
	public void test() {
		DesktopAgent desktopAgent = connect();
		ComponentAgent cbm1 = desktopAgent.query("#w #cbm1");
		assertEquals(5, cbm1.getChildren().size());

		desktopAgent.query("#serialization").click();
		cbm1 = desktopAgent.query("#w #cbm1");
		assertEquals(5, cbm1.getChildren().size());

		assertTrue(desktopAgent.query("#msg").as(Label.class).getValue().contains("done deserialize:"), "Should be able to serialized");
	}
}
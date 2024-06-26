/* F95_ZK_4582Test.java

		Purpose:
		
		Description:
		
		History:
				Fri May 22 12:05:58 CST 2020, Created by leon

Copyright (C) 2020 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zktest.zats.ZATSTestCase;
import org.zkoss.zul.Label;

public class F95_ZK_4582Test extends ZATSTestCase {
	@Test
	public void test() {
		DesktopAgent desktop = connect();
		desktop.query("#btn1").click();
		Assertions.assertEquals("1", desktop.query("#lb1").as(Label.class).getValue());
		desktop.query("#btn2").click();
		Assertions.assertEquals("one", desktop.query("#lb1").as(Label.class).getValue());
		Assertions.assertEquals("two", desktop.query("#lb2").as(Label.class).getValue());
		Assertions.assertEquals("three", desktop.query("#lb3").as(Label.class).getValue());
	}
}

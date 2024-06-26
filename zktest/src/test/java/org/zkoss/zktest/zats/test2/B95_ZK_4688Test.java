/* B95_ZK_4688Test.java

	Purpose:
		
	Description:
		
	History:
		Mon Oct 5 11:18:11 CST 2020, Created by jameschu

Copyright (C) 2020 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zktest.zats.ZATSTestCase;
import org.zkoss.zul.Label;

/**
 * @author jameschu
 */
public class B95_ZK_4688Test extends ZATSTestCase {
	@Test
	public void test() {
		DesktopAgent desktop = connect();
		List<ComponentAgent> btns = desktop.queryAll("button");
		btns.get(0).click();
		Label resultLabel = desktop.query("#result").as(Label.class);
		Assertions.assertTrue(resultLabel.getValue().endsWith("1,null"));
		btns.get(1).click();
		Assertions.assertTrue(resultLabel.getValue().equals("0,null"));
	}
}

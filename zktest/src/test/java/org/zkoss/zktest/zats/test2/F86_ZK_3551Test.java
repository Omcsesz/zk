/* F86_ZK_3551Test.java

	Purpose:
		
	Description:
		
	History:
		Thu Sep 27 19:14:32 CST 2018, Created by rudyhuang

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;
import org.zkoss.test.webdriver.ztl.JQuery;

/**
 * @author rudyhuang
 */
public class F86_ZK_3551Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();

		click(jq("@button:eq(0)"));
		waitResponse();
		Assertions.assertTrue(jq("@menupopup:visible @menuitem:eq(0)").hasClass("z-menuitem-hover"));

		click(jq("@button:eq(1)"));
		waitResponse();
		Assertions.assertTrue(jq("$aualert").isVisible());
		closeAuAlert();

		click(jq("@button:eq(2)"));
		waitResponse();
		Assertions.assertTrue(jq("$aualert").isVisible());
		closeAuAlert();

		click(jq("@button:eq(3)"));
		waitResponse();
		Assertions.assertTrue(jq("$aualert").isVisible());
		closeAuAlert();

		click(jq("@button:eq(4)"));
		waitResponse();
		Assertions.assertTrue(jq("@menupopup:visible @menu:eq(0)").hasClass("z-menu-hover"));

		click(jq("@button:eq(0)"));
		waitResponse();
		Assertions.assertTrue(jq("@menupopup:visible @menuitem:eq(0)").hasClass("z-menuitem-hover"));
		Assertions.assertFalse(jq("@menupopup:visible @menu:eq(0)").hasClass("z-menu-hover"));

		// Index out of bound
		click(jq("@button:eq(5)"));
		waitResponse();
		Assertions.assertTrue(jq("$aualert").isVisible());
		closeAuAlert();

		click(jq("@button:eq(6)"));
		waitResponse();
		Assertions.assertTrue(jq("$aualert").isVisible());
		closeAuAlert();
	}

	private void closeAuAlert() {
		JQuery $aualert = jq("$aualert");
		if ($aualert.isVisible()) {
			click($aualert.find("@button"));
			waitResponse();
		}
	}
}

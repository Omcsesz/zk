/* B95_ZK_3316Test.java

	Purpose:

	Description:

	History:
		Wed Dec 16 16:12:32 CST 2020, Created by katherinelin

Copyright (C) 2020 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;
import org.zkoss.test.webdriver.ztl.JQuery;

public class B95_ZK_3316Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();
		waitResponse();
		JQuery jqBar = jq(".z-columns-bar");
		JQuery grid1 = jq("$grid1");
		JQuery grid2 = jq("$grid2");
		JQuery grid3 = jq("$grid3");
		assertEquals(grid1.find(".z-row-content:eq(0)").outerWidth() + jqBar.outerWidth(), grid1.width(), 1);
		grid1.scrollTop(100);
		waitResponse();
		assertEquals(grid1.find(".z-row-content:eq(0)").outerWidth() + jqBar.outerWidth(), grid1.width(), 1);
		assertEquals(grid2.find(".z-row-content:eq(0)").outerWidth(), grid2.width(), 1);
		assertNotEquals(grid3.find(".z-rows").height(), grid3.find(".z-grid-body").height(), 1);
	}
}

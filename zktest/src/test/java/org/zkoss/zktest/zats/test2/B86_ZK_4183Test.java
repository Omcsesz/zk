/* B86_ZK_4183Test.java

        Purpose:
                
        Description:
                
        History:
                Thu Jan 03 10:03:21 CST 2019, Created by charlesqiu

Copyright (C) 2019 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;
import org.zkoss.test.webdriver.ztl.JQuery;

public class B86_ZK_4183Test extends WebDriverTestCase {

	@Test
	public void test() {
		connect();
		testTableVisible(jq(".z-grid-header"));
		testTableVisible(jq(".z-grid-body"));
		testTableVisible(jq(".z-grid-footer"));
		testTableVisible(jq(".z-listbox-header"));
		testTableVisible(jq(".z-listbox-body"));
		testTableVisible(jq(".z-listbox-footer"));
		testTableVisible(jq(".z-tree-header"));
		testTableVisible(jq(".z-tree-body"));
		testTableVisible(jq(".z-tree-footer"));
	}

	private void testTableVisible(JQuery dom) {
		Assertions.assertEquals("visible", dom.find("table").css("visibility"));
	}
}

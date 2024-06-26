/* B95_ZK_4640Test.java

	Purpose:
		
	Description:
		
	History:
		Fri Aug 14 16:04:29 CST 2020, Created by rudyhuang

Copyright (C) 2020 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;

/**
 * @author rudyhuang
 */
public class B95_ZK_4640Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();

		click(jq("@button:eq(0)"));
		waitResponse();
		click(jq("@button:eq(1)"));
		waitResponse();
		String old = getZKLog();
		closeZKLog();

		for (int i = 1; i < 10; i++) {
			click(jq("@button:eq(0)"));
			waitResponse();
		}

		click(jq("@button:eq(1)"));
		waitResponse();
		Assertions.assertEquals(old, getZKLog(), "the count is different");
	}
}

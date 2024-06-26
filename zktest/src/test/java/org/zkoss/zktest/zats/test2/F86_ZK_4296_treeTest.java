/* F86_ZK_4296_treeTest.java

		Purpose:
		
		Description:
		
		History:
				Fri Sep 20 12:32:46 CST 2019, Created by leon

Copyright (C) 2019 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;

public class F86_ZK_4296_treeTest extends WebDriverTestCase {
	@Test
	public void test() {
		connect();
		waitResponse();
		click(jq("@button").eq(0));
		waitResponse();
		click(jq("@button").eq(2));
		waitResponse();
		errorCheck(true);
		click(jq("@button").eq(4));
		waitResponse();
		errorCheck(true);
		click(jq("@button").eq(1));
		waitResponse();
		click(jq("@button").eq(2));
		waitResponse();
		click(jq("@button").eq(0));
		waitResponse();
		errorCheck(true);
		click(jq("@button").eq(4));
		waitResponse();
		errorCheck(true);
		click(jq("@button").eq(3));
		waitResponse();
		click(jq("@button").eq(4));
		waitResponse();
		click(jq("@button").eq(0));
		waitResponse();
		errorCheck(true);
		click(jq("@button").eq(2));
		waitResponse();
		errorCheck(true);
		click(jq("@button").eq(5));
		waitResponse();
		click(jq("@button").eq(2));
		waitResponse();
		errorCheck(false);
	}
	
	private void errorCheck(Boolean expectError) {
		if (expectError) {
			Assertions.assertTrue(hasError());
			click(jq("@button:contains(OK)"));
			waitResponse();
		} else {
			Assertions.assertFalse(hasError());
		}
	}
}

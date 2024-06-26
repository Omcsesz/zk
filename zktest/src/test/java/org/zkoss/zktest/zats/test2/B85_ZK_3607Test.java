/* B85_ZK_3607Test.java

	Purpose:
		
	Description:
		
	History:
		Wed Jul 12 11:14:16 CST 2017, Created by rudyhuang

Copyright (C) 2017 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;

/**
 * @author rudyhuang
 */
public class B85_ZK_3607Test extends WebDriverTestCase {
	@Test
	public void test() throws Exception {
		connect();

		String typeString = "abab";
		type(jq("@combobox .z-combobox-input"), typeString);
		waitResponse();

		Assertions.assertEquals(typeString, jq("@label:eq(1)").text());
	}
}

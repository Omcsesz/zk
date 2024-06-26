/* B85_ZK_3322Test.java

	Purpose:
		
	Description:
		
	History:
		Fri Jan 10 15:38:26 CST 2018, Created by klyve

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;



/**
 * @author klyvechen
 */
public class B85_ZK_3322Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();
		waitResponse();

		click(jq("@button").get(0));
		waitResponse();
		click(jq("@button").get(0));
		waitResponse();
		click(jq("@button").get(0));
		waitResponse();
		Assertions.assertFalse(jq(".z-window-header").exists());

		click(jq("@button").get(2));
		waitResponse();
		click(jq("@button").get(2));
		waitResponse();
		click(jq("@button").get(2));
		waitResponse();
		Assertions.assertFalse(jq(".z-window-header").exists());

	}
}

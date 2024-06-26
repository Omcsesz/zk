/* B70_ZK_2987Test.java

	Purpose:
		
	Description:
		
	History:
		Mon Apr 08 12:48:14 CST 2019, Created by rudyhuang

Copyright (C) 2019 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;
import org.zkoss.test.webdriver.ztl.JQuery;

/**
 * @author rudyhuang
 */
public class B70_ZK_2987Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();

		JQuery body = jq("@listbox .z-listbox-body");
		body.scrollTop(body.scrollHeight());
		waitResponse();

		click(jq(".z-paging-button.z-paging-last"));
		waitResponse();
		JQuery item250 = jq("@listitem:contains(Item 250)");
		Assertions.assertEquals(body.offsetTop() + body.height(), item250.offsetTop() + item250.height(), 1);

		click(jq(".z-paging-button.z-paging-previous"));
		waitResponse();
		JQuery item150 = jq("@listitem:contains(Item 150)");
		Assertions.assertEquals(body.offsetTop() + body.height(), item150.offsetTop() + item150.height(), 1);
	}
}

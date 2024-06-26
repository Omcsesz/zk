/* F50_ZK_239Test.java

	Purpose:
		
	Description:
		
	History:
		Fri Apr 12 15:44:51 CST 2019, Created by rudyhuang

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
public class F50_ZK_239Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();

		JQuery div1 = jq("@hlayout @div:first");
		JQuery div2 = jq("@hlayout @div:last");
		Assertions.assertEquals(5, div2.offsetLeft() - (div1.offsetLeft() + div1.outerWidth()));

		JQuery div3 = jq("@vlayout @div:first");
		JQuery div4 = jq("@vlayout @div:last");
		Assertions.assertEquals(5, div4.offsetTop() - (div3.offsetTop() + div1.outerHeight()));
	}
}

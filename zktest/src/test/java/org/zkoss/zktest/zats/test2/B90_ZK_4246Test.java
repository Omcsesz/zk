/* B90_ZK_4246Test.java

	Purpose:
		
	Description:
		
	History:
		Wed Mar 18 12:28:18 CST 2020, Created by rudyhuang

Copyright (C) 2020 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;
import org.zkoss.test.webdriver.ztl.Element;

/**
 * @author rudyhuang
 */
public class B90_ZK_4246Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();

		Element cave = widget("@grid").$n("cave");
		Assertions.assertNotEquals("hidden", jq(cave).css("visibility"));
	}
}

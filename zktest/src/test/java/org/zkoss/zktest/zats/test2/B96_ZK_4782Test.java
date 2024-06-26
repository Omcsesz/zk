/* B96_ZK_4782Test.java

	Purpose:
		
	Description:
		
	History:
		Wed Apr 7 11:50:21 CST 2021, Created by jameschu

Copyright (C) 2021 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;

/**
 * @author jameschu
 */

public class B96_ZK_4782Test extends WebDriverTestCase {

	@Test
	public void test() throws Exception {
		connect();
		waitResponse();
		assertEquals(jq("$d1").height(), jq("$d2").height());
	}
}

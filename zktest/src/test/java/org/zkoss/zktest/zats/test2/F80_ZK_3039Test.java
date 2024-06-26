/* F80_ZK_3039Test.java

	Purpose:
		
	Description:
		
	History:
		Sat Jun  4 14:00:31 CST 2016, Created by Chris

Copyright (C)  Potix Corporation. All Rights Reserved.

This program is distributed under LGPL Version 2.1 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
package org.zkoss.zktest.zats.test2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;

/**
 *
 * @author Chris
 */
public class F80_ZK_3039Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();
		// expecting to see the string amazing show up twice
		assertEquals(2, jq(".z-div > .z-label:contains(\"amazing\")").length());
	}
}

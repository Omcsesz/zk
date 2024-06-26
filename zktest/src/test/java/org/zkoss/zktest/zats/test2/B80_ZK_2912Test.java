/* B80_ZK_2912Test.java

	Purpose:
		
	Description:
		
	History:
		6:32 PM 12/22/15, Created by jumperchen

Copyright (C) 2015 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;

/**
 * @author jumperchen
 */
public class B80_ZK_2912Test extends WebDriverTestCase {
	@Test
	public void testZK2912() {
		connect();
		assertEquals("You should see \"Label Text\" below.Label Text", trim(jq("@label").text()));
	}
}

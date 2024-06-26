/* B96_ZK_5034Test.java

		Purpose:
		
		Description:
		
		History:
				Tue April 19 09:55:14 CST 2022, Created by jameschu

Copyright (C) 2022 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;

public class B96_ZK_5034Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();
		jq(".z-frozen .z-frozen-inner").scrollLeft(1000);
		waitResponse();
		Assertions.assertFalse(jq(".z-columns-bar").offsetLeft() > 600);
	}
}
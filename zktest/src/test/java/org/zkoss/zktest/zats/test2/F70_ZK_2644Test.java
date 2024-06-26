/* F70_ZK_2644Test.java

	Purpose:
		
	Description:
		
	History:
		Wed Apr 17 17:41:00 CST 2019, Created by rudyhuang

Copyright (C) 2019 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import org.zkoss.test.webdriver.WebDriverTestCase;

/**
 * @author rudyhuang
 */
public class F70_ZK_2644Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();

		click(jq("@button"));
		waitResponse();
		getActions().sendKeys(Keys.ESCAPE).perform();
		waitResponse();
		getActions().keyDown(Keys.CONTROL).sendKeys("k").keyUp(Keys.CONTROL).perform();
		waitResponse();
		Assertions.assertEquals("keydown", getZKLog());
	}
}

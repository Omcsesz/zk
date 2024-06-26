/* B50_3210356Test.java

		Purpose:
                
		Description:
                
		History:
				Fri Mar 22 10:20:00 CST 2019, Created by charlesqiu

Copyright (C) 2019 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import org.zkoss.test.webdriver.WebDriverTestCase;
import org.zkoss.test.webdriver.ztl.JQuery;

public class B50_3210356Test extends WebDriverTestCase {

	@Test
	public void test() {
		connect();

		JQuery input = jq(".z-timebox-input");
		focus(input);
		waitResponse();
		for (int i = 0; i < 5; i++) {
			sendKeys(input, Keys.ARROW_LEFT);
			waitResponse();
		}
		sendKeys(input, Keys.ARROW_UP);
		waitResponse();
		Assertions.assertEquals("1", input.val().split(":")[0]);
	}
}

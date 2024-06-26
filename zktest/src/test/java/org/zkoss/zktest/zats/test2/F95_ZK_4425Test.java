/* F95_ZK_4425Test.java

	Purpose:
		
	Description:
		
	History:
		Tue Sep 1 17:59:06 CST 2020, Created by jameschu

Copyright (C) 2020 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;
import org.zkoss.test.webdriver.ztl.JQuery;

/**
 * @author jameschu
 */
public class F95_ZK_4425Test extends WebDriverTestCase {
	@Test
	public void test() throws Exception {
		connect();

		getActions().clickAndHold(driver.findElement(jq(".z-rangeslider-track")))
				.moveByOffset(100, 0)
				.release()
				.perform();
		waitResponse();
		Assertions.assertNotEquals(0, jq("@rangeslider .z-sliderbuttons-button").last().positionLeft());

		final JQuery sliderFirstBtn = jq("@multislider .z-sliderbuttons-button").first();
		final int origPositionLeft = sliderFirstBtn.positionLeft();
		getActions().clickAndHold(driver.findElement(jq(".z-multislider-track")))
				.moveByOffset(-100, 0)
				.release()
				.perform();
		waitResponse();
		Assertions.assertNotEquals(origPositionLeft, sliderFirstBtn.positionLeft());
	}
}

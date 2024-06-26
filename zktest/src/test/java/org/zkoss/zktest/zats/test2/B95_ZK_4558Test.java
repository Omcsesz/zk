/* B95_ZK_4558Test.java

	Purpose:
		
	Description:
		
	History:
		Fri, Aug 14, 2020  02:48:18 PM, Created by jameschu

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
public class B95_ZK_4558Test extends WebDriverTestCase {
	private static final int DRAG_THRESHOLD = 2;
	@Test
	public void test() {
		connect();
		click(jq("$btn1"));
		waitResponse();
		JQuery h2 = jq("$l1_header2");
		int h2Width = h2.outerWidth();
		getActions().moveToElement(toElement(h2), h2Width - DRAG_THRESHOLD, 15)
				.clickAndHold()
				.moveByOffset(-20, 0)
				.release()
				.perform();
		waitResponse();
		click(jq("$btn1"));
		waitResponse();
		Assertions.assertTrue(jq("$l1_header1").width() >= 1);
		// second listbox
		h2 = jq("$l2_header2");
		h2Width = h2.outerWidth();
		getActions().moveToElement(toElement(h2), h2Width - DRAG_THRESHOLD, 15)
				.clickAndHold()
				.moveByOffset(-20, 0)
				.release()
				.perform();
		waitResponse();
		click(jq("$btn2"));
		waitResponse();
		Assertions.assertTrue(jq("$l2_header1").width() >= 1);
	}
}

/** B80_ZK_3139Test.java.

 Purpose:

 Description:

 History:
 	Tue May 31 15:00:22 CST 2016, Created by jameschu

 Copyright (C) 2016 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.zktest.zats.test2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;
import org.zkoss.test.webdriver.ztl.JQuery;

/**
 * @author jameschu
 *
 */
public class B80_ZK_3139Test extends WebDriverTestCase{

    @Test
    public void test() {
		connect();

		// this case cannot work with A11Y as the fixed ZK-5213: Improve accessibility by add tooltips on icons
		if (!Boolean.valueOf(getEval("!!window.za11y"))) {
			JQuery p = jq("@panel");

			JQuery maximize1 = p.find(".z-panel-maximize");
			assertEquals("Maximize", maximize1.attr("title"));

			click(maximize1);
			waitResponse();
			assertEquals("Restore", maximize1.attr("title"));

			JQuery w = jq("@window");

			JQuery maximize2 = w.find(".z-window-maximize");
			assertEquals("Maximize", maximize2.attr("title"));

			click(maximize2);
			waitResponse();
			assertEquals("Restore", maximize2.attr("title"));
		}
	}
}
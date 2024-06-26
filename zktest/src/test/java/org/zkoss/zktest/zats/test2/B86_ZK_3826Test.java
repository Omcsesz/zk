/* B86_ZK_3826Test.java

        Purpose:
                
        Description:
                
        History:
                Wed Aug 22 18:20:56 CST 2018, Created by charlesqiu

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;
import org.zkoss.test.webdriver.ztl.JQuery;

public class B86_ZK_3826Test extends WebDriverTestCase {

	private JQuery calenderTitle;
	private JQuery calenderText;
	private JQuery calendarCells;

	@Test
	public void test() {
		connect();
		calenderTitle = jq(".z-calendar-title");
		calenderText = jq(".z-calendar-text");
		calendarCells = jq(".z-calendar-cell");
		testYearView();
		testDecadeView();
	}

	private void testYearView() {
		click(calenderTitle);
		waitResponse();
		Assertions.assertEquals(getFirstCellText(), getStartYearInTitle());
		Assertions.assertEquals(getLastCellText(), getEndYearInTitle());
	}

	private void testDecadeView() {
		click(calenderTitle);
		waitResponse();
		Assertions.assertEquals(getFirstCellText().split("-")[0].trim(), getStartYearInTitle());
		Assertions.assertEquals(getLastCellText().split("-")[1].trim(), getEndYearInTitle());
	}

	private String getFirstCellText() {
		return calendarCells.first().text();
	}

	private String getLastCellText() {
		return calendarCells.last().text();
	}

	private String getStartYearInTitle() {
		return calenderText.text().split("-")[0].trim();
	}

	private String getEndYearInTitle() {
		return calenderText.text().split("-")[1].trim();
	}
}

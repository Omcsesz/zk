package org.zkoss.zephyr.webdriver.mvvm.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.zkoss.test.webdriver.WebDriverTestCase;
import org.zkoss.test.webdriver.ztl.JQuery;


public class F01845TabboxModel2Test extends WebDriverTestCase {

	@Test
	public void test() {
		connect();

		JQuery msg = jq("$msg");
		JQuery tbox = jq("$tbox");
		JQuery add = jq("$add");
		JQuery remove = jq("$remove");
		JQuery tabs = tbox.find("@tab");

		assertEquals("0", msg.text());
		assertEquals(2, tabs.length());

		click(tabs.eq(1));
		waitResponse();
		assertEquals("1", msg.text());

		click(add);
		waitResponse();
		click(add);
		waitResponse();
		tabs = tbox.find("@tab");
		assertEquals("1", msg.text());
		assertEquals(4, tabs.length());

		click(tabs.eq(2));
		waitResponse();
		assertEquals("2", msg.text());

		click(remove);
		waitResponse();
		tabs = tbox.find("@tab");
		assertEquals("2", msg.text());
		assertEquals(3, tabs.length());
	}
}
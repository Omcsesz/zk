package org.zkoss.zktest.zats.test2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import org.zkoss.test.webdriver.WebDriverTestCase;

public class B80_ZK_3095Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();
		int miow = jq(".z-menuitem").eq(0).width();
		assertFalse(jq(".z-menubar-left").isVisible());
		assertFalse(jq(".z-menubar-right").isVisible());
		System.out.println(miow);
		driver.manage().window().setSize(new Dimension((miow + 12) * 5 + 10, 500));
		waitResponse(true);
		assertTrue(jq(".z-menubar-left").isVisible());
		assertTrue(jq(".z-menubar-right").isVisible());
	}
}

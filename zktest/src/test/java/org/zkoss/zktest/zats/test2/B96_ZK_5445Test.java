/* B96_ZK_5445Test.java

	Purpose:
		
	Description:
		
	History:
		4:51 PM 2023/4/26, Created by jumperchen

Copyright (C) 2023 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoAlertPresentException;

import org.zkoss.test.webdriver.WebDriverTestCase;

/**
 * @author jumperchen
 */
public class B96_ZK_5445Test extends WebDriverTestCase {
	@Test
	public void testReflectedXSSAttack() {
		connect();
		try {
			assertNotEquals(driver.switchTo().alert().getText(), "1");
			fail("cannot run into this line, otherwise, the bug exists.");
		} catch (NoAlertPresentException e) {
			// yes, it works here.
		}
		assertTrue(hasError());
	}
}

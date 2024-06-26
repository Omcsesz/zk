/* Z60_Touch_024Test.java

		Purpose:
		
		Description:
		
		History:
				Fri Jun 21 16:18:46 CST 2019, Created by leon

Copyright (C) 2019 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.chrome.ChromeOptions;

import org.zkoss.test.webdriver.ExternalZkXml;
import org.zkoss.test.webdriver.ForkJVMTestOnly;
import org.zkoss.test.webdriver.WebDriverTestCase;

@ForkJVMTestOnly
public class Z60_Touch_024Test extends WebDriverTestCase {
	@RegisterExtension
	public static final ExternalZkXml CONFIG = new ExternalZkXml("/test2/enable-tablet-ui-zk.xml");

	@Override
	protected ChromeOptions getWebDriverOptions() {
		return super.getWebDriverOptions()
				.setExperimentalOption("mobileEmulation", Collections.singletonMap("deviceName", "iPad"));
	}
	
	@Test
	public void test() {
		connect();
		waitResponse();
		
		int originScrollHeight = jq(".z-tabpanel").scrollHeight();
		MatcherAssert.assertThat(originScrollHeight, greaterThan(jq(".z-tabpanel").height()));
		
		click(jq("@button").eq(0));
		waitResponse();
		MatcherAssert.assertThat(originScrollHeight, lessThan(jq(".z-tabpanel").scrollHeight()));
		
		click(jq("@button").eq(1));
		waitResponse();
		assertEquals(originScrollHeight, jq(".z-tabpanel").scrollHeight());
		
		click(jq("@button").eq(2));
		waitResponse();
		MatcherAssert.assertThat(originScrollHeight, greaterThan(jq(".z-tabpanel").scrollHeight()));
		
		click(jq("@button").eq(2));
		waitResponse();
		assertEquals(originScrollHeight, jq(".z-tabpanel").scrollHeight());
	}
}

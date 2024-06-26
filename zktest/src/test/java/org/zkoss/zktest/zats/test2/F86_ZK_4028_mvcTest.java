/* F86_ZK_4028_mvcTest.java

	Purpose:
		
	Description:
		
	History:
		Thu Aug 16 17:39:07 CST 2018, Created by rudyhuang

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zktest.zats.ZATSTestCase;

/**
 * @author rudyhuang
 */
public class F86_ZK_4028_mvcTest extends ZATSTestCase {
	@Test
	public void test() {
		DesktopAgent desktop = connect();

		desktop.query("a[label='Step 2']").click();
		Assertions.assertNotNull(desktop.query("label[value='Step 2']"));

		desktop.query("a[label='Step 2-2']").click();
		Assertions.assertNotNull(desktop.query("label[value='Step 2-2']"));

		desktop.query("a[label='Step 2-2-1']").click();
		Assertions.assertNotNull(desktop.query("label[value='Step 2-2-1']"));

		desktop.query("#btn").click();
		Assertions.assertNotNull(desktop.query("label[value='Step 1']"));
		Assertions.assertNotNull(desktop.query("label[value='Step 1-1']"));
	}
}

/* NotifyChangeOnSetterTest.java

	Purpose:
		
	Description:
		
	History:
		Wed May 05 11:52:43 CST 2021, Created by rudyhuang

Copyright (C) 2021 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.bind.viewmodel.notification;

import org.junit.Assert;
import org.junit.Test;

import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zktest.zats.ZATSTestCase;
import org.zkoss.zul.Label;

/**
 * @author rudyhuang
 */
public class NotifyChangeAutoTest extends ZATSTestCase {
	@Test
	public void testByAnnotation() {
		final DesktopAgent desktop = connect("/bind/viewmodel/notification/notifychange-auto.zul");
		final Label fullname = desktop.query("#fullname").as(Label.class);
		final Label fn = desktop.query("#firstname").as(Label.class);
		final Label ln = desktop.query("#lastname").as(Label.class);
		desktop.query("button").click();
		Assert.assertNotEquals("John", fn.getValue());
		Assert.assertNotEquals("Smith", ln.getValue());
		// FIXME ZK-4891 Assert.assertNotEquals("John Smith", fullname.getValue());
	}

	@Test
	public void testBySysProp() {
		final DesktopAgent desktop = connect("/bind/viewmodel/notification/notifychange-auto-sysprop.zul");
		try {
			final Label fullname = desktop.query("#fullname").as(Label.class);
			final Label fn = desktop.query("#firstname").as(Label.class);
			final Label ln = desktop.query("#lastname").as(Label.class);
			desktop.query("#rnd").click();
			Assert.assertNotEquals("John", fn.getValue());
			Assert.assertNotEquals("Smith", ln.getValue());
			// FIXME ZK-4891 Assert.assertNotEquals("John Smith", fullname.getValue());
		} finally {
			desktop.query("#restore").click();
		}
	}
}

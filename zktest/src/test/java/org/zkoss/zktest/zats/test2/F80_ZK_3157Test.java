/* F80_ZK_3157Test.java

	Purpose:
		
	Description:
		
	History:
		Wed Apr 6 14:14:06 CST 2016, Created by jameschu

Copyright (C) 2016 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.zkoss.lang.Library;
import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zktest.zats.ZATSTestCase;

/**
 * @author jameschu
 */
public class F80_ZK_3157Test extends ZATSTestCase {

	@Test
	public void test() {
		DesktopAgent desktop = connect();
		List<ComponentAgent> buttons = desktop.queryAll("button");
		buttons.get(0).click();
		List<String> props = Library.getProperties("3157");
		assertEquals("[set1]", props.toString());
		buttons.get(1).click();
		props = Library.getProperties("3157");
		assertEquals("[set1, add1]", props.toString());
		buttons.get(2).click();
		props = Library.getProperties("3157");
		assertEquals("[set2-01, set2-02]", props.toString());
		buttons.get(3).click();
		props = Library.getProperties("3157");
		assertEquals("[set2-01, set2-02, add2-01, add2-02]", props.toString());
	}

}

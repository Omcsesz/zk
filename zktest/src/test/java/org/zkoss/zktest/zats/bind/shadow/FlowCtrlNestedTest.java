/* FlowCtrlNestedTest.java

	Purpose:
		
	Description:
		
	History:
		Fri May 07 15:33:40 CST 2021, Created by rudyhuang

Copyright (C) 2021 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.bind.shadow;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zktest.zats.ZATSTestCase;
import org.zkoss.zul.Label;

/**
 * @author rudyhuang
 */
public class FlowCtrlNestedTest extends ZATSTestCase {
	@Test
	public void test() {
		final DesktopAgent desktop = connect("/bind/shadow/flowctrl-nested.zul");
		desktop.query("#btnS").click();
		final List<ComponentAgent> items = desktop.queryAll("hlayout");
		Assertions.assertEquals(20, items.size());
		Assertions.assertEquals("1", getLabelTrimmedValue(items.get(0).getChild(0))); // i=1
		Assertions.assertEquals("Fizz", getLabelTrimmedValue(items.get(2).getChild(0))); // i=3
		Assertions.assertEquals("Buzz", getLabelTrimmedValue(items.get(4).getChild(0))); // i=5
		Assertions.assertEquals("Fizz", getLabelTrimmedValue(items.get(14).getChild(0))); // i=15 part1
		Assertions.assertEquals("Buzz", getLabelTrimmedValue(items.get(14).getChild(1))); // i=15 part2

		desktop.query("#btnL").click();
		final List<ComponentAgent> items2 = desktop.queryAll("hlayout");
		Assertions.assertEquals(51, items2.size());
		Assertions.assertEquals("Buzz", getLabelTrimmedValue(items2.get(0).getChild(0))); // i=50
		Assertions.assertEquals("Fizz", getLabelTrimmedValue(items2.get(1).getChild(0))); // i=51
		Assertions.assertEquals("52", getLabelTrimmedValue(items2.get(2).getChild(0))); // i=52
		Assertions.assertEquals("Fizz", getLabelTrimmedValue(items2.get(10).getChild(0))); // i=60 part1
		Assertions.assertEquals("Buzz", getLabelTrimmedValue(items2.get(10).getChild(1))); // i=60 part2
	}

	private String getLabelTrimmedValue(ComponentAgent lbl) {
		return lbl.as(Label.class).getValue().trim();
	}
}

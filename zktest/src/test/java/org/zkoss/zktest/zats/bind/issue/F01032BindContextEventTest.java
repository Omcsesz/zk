package org.zkoss.zktest.zats.bind.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zktest.zats.ZATSTestCase;
import org.zkoss.zul.Label;

public class F01032BindContextEventTest extends ZATSTestCase {
	
	@Test
	public void test() {
		DesktopAgent desktop = connect();

		ComponentAgent msg = desktop.query("#msg");
		ComponentAgent tb = desktop.query("#tb");
		ComponentAgent btn = desktop.query("#btn");
		
		tb.type("a");
		assertEquals("evt1:onChange,evt2:onChange, cmd:cmd", msg.as(Label.class).getValue());
		
		btn.click();
		assertEquals("evt1:onClick,evt2:onClick, cmd:cmd", msg.as(Label.class).getValue());
	}
}

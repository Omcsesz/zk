package org.zkoss.zktest.zats.bind.issue;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zktest.zats.ZATSTestCase;

public class B01347CheckboxCrashTest extends ZATSTestCase{
	@Test
	public void test() {
		DesktopAgent desktop = connect();
		
		ComponentAgent listbox = desktop.query("listbox");
		assertTrue(listbox != null);	
	}
}

package org.zkoss.zktest.zats.test2;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zktest.zats.ZATSTestCase;

public class F80_ZK_2662Test extends ZATSTestCase {
	@Test
	public void test() {
		try {
			DesktopAgent desktop = connect();
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
}

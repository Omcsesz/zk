/* TextboxTest.java

		Purpose:
		
		Description:
		
		History:
				Mon Jul 06 17:41:54 CST 2020, Created by leon

Copyright (C) 2020 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.wcag;

import org.junit.jupiter.api.Test;

public class TextboxTest extends WcagTestCase {
	@Test
	public void test() {
		connect();
		verifyA11y();
	}
}

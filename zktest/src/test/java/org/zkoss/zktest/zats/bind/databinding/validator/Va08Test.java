/* Va08Test.java

		Purpose:
		
		Description:
		
		History:
				Tue May 11 11:03:59 CST 2021, Created by jameschu

Copyright (C) 2021 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.bind.databinding.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zktest.zats.ZATSTestCase;
import org.zkoss.zul.Label;

/**
 * @author jameschu
 */
public class Va08Test extends ZATSTestCase {
	@Test
	public void test() {
		DesktopAgent desktopAgent = connect();
		ComponentAgent keywordBoxAgent = desktopAgent.query("#keywordBox");
		Label keywordLabel = desktopAgent.query("#keywordLabel").as(Label.class);

		keywordBoxAgent.input("123");
		assertEquals("123", keywordLabel.getValue());

		keywordBoxAgent.input("1234");
		assertEquals("123", keywordLabel.getValue());
	}
}

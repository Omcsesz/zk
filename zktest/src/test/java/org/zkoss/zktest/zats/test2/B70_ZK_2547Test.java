package org.zkoss.zktest.zats.test2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zats.mimic.operation.OpenAgent;
import org.zkoss.zktest.zats.ZATSTestCase;
import org.zkoss.zul.Treeitem;

public class B70_ZK_2547Test extends ZATSTestCase {
	@Test
	public void test() {
		DesktopAgent desktop = connect();
		
		ComponentAgent tree = desktop.query("#myTreeA");
		List<ComponentAgent> treeitems = tree.queryAll("treeitem");
		
		treeitems.get(3).as(OpenAgent.class).open(true);
		treeitems.get(1).as(OpenAgent.class).open(true);
		treeitems.get(0).as(OpenAgent.class).open(true);
		assertEquals(true, treeitems.get(0).as(Treeitem.class).isOpen());
		assertEquals(true, treeitems.get(1).as(Treeitem.class).isOpen());
		assertEquals(true, treeitems.get(3).as(Treeitem.class).isOpen());
		
		treeitems.get(1).as(OpenAgent.class).open(false);
		treeitems = tree.queryAll("treeitem");
		assertEquals(true, treeitems.get(0).as(Treeitem.class).isOpen());
		assertEquals(false, treeitems.get(3).as(Treeitem.class).isOpen());
		assertEquals(true, treeitems.get(5).as(Treeitem.class).isOpen());
	}
}

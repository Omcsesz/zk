/* B80_ZK_1696_listTest.java

	Purpose:
		
	Description:
		
	History:
		Tue Aug 11 16:59:10 CST 2015, Created by Christopher

Copyright (C)  Potix Corporation. All Rights Reserved.

This program is distributed under LGPL Version 2.1 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
package org.zkoss.zktest.zats.test2;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zats.mimic.operation.PagingAgent;
import org.zkoss.zktest.zats.ZATSTestCase;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;

/**
 * 
 * @author Christopher
 */
public class B80_ZK_1696_listTest extends ZATSTestCase {
	@Test
	public void test(){
		DesktopAgent desktop = connect();
		ComponentAgent listAgent = desktop.query("#list");
		Listbox mylist = listAgent.as(Listbox.class);
		Assertions.assertNotNull(mylist);
		
		//check default render result
		Assertions.assertEquals(0, mylist.getActivePage());
		List<ComponentAgent> listcells = listAgent.queryAll("listcell");
		Assertions.assertEquals("item 1", listcells.get(0).as(Listcell.class).getLabel());
		Assertions.assertEquals("item 2", listcells.get(1).as(Listcell.class).getLabel());
		Assertions.assertEquals("item 3", listcells.get(2).as(Listcell.class).getLabel());
		
		//switch to 2nd page
		PagingAgent paging = desktop.query("listbox > paging").as(PagingAgent.class);
		paging.moveTo(1);
		//check that render result after switching page
		Assertions.assertEquals(1, mylist.getActivePage());
		listcells = listAgent.queryAll("listcell");
		Assertions.assertEquals("item 4", listcells.get(0).as(Listcell.class).getLabel());
		Assertions.assertEquals("item 5", listcells.get(1).as(Listcell.class).getLabel());
		Assertions.assertEquals("item 6", listcells.get(2).as(Listcell.class).getLabel());
		
		desktop.query("#btn4").click(); //switch to 2nd model
		//check render result after changing model
		Assertions.assertEquals(1, mylist.getActivePage());
		listcells = listAgent.queryAll("listcell");
		Assertions.assertEquals("item d", listcells.get(0).as(Listcell.class).getLabel());
		Assertions.assertEquals("item e", listcells.get(1).as(Listcell.class).getLabel());
		Assertions.assertEquals("item f", listcells.get(2).as(Listcell.class).getLabel());
		
		paging.moveTo(2); //2nd model change to page 3
		//check render result after changing model
		Assertions.assertEquals(2, mylist.getActivePage());
		listcells = listAgent.queryAll("listcell");
		Assertions.assertEquals("item g", listcells.get(0).as(Listcell.class).getLabel());
		Assertions.assertEquals("item h", listcells.get(1).as(Listcell.class).getLabel());
		Assertions.assertEquals("item i", listcells.get(2).as(Listcell.class).getLabel());
		
		desktop.query("#btn1").click(); //switch back to 1st model
		//check render result after switching back to original model
		Assertions.assertEquals(1, mylist.getActivePage());
		listcells = listAgent.queryAll("listcell");
		Assertions.assertEquals("item 4", listcells.get(0).as(Listcell.class).getLabel());
		Assertions.assertEquals("item 5", listcells.get(1).as(Listcell.class).getLabel());
		Assertions.assertEquals("item 6", listcells.get(2).as(Listcell.class).getLabel());
	}
	
	@Test
	public void testApi(){
		DesktopAgent desktop = connect();
		ComponentAgent listAgent = desktop.query("#list");
		Listbox mylist = listAgent.as(Listbox.class);
		Assertions.assertNotNull(mylist);
		
		//check default render result
		Assertions.assertEquals(0, mylist.getActivePage());
		List<ComponentAgent> listcells = listAgent.queryAll("listcell");
		Assertions.assertEquals("item 1", listcells.get(0).as(Listcell.class).getLabel());
		Assertions.assertEquals("item 2", listcells.get(1).as(Listcell.class).getLabel());
		Assertions.assertEquals("item 3", listcells.get(2).as(Listcell.class).getLabel());
		
		desktop.query("#btn4").click(); //switch to 2nd model
		//check render result after changing model
		Assertions.assertEquals(0, mylist.getActivePage());
		listcells = listAgent.queryAll("listcell");
		Assertions.assertEquals("item a", listcells.get(0).as(Listcell.class).getLabel());
		Assertions.assertEquals("item b", listcells.get(1).as(Listcell.class).getLabel());
		Assertions.assertEquals("item c", listcells.get(2).as(Listcell.class).getLabel());

		desktop.query("#btn2").click(); //switch back to 1st model
		//check render result after switching back to original model
		Assertions.assertEquals(1, mylist.getActivePage());
		listcells = listAgent.queryAll("listcell");
		Assertions.assertEquals("item 4", listcells.get(0).as(Listcell.class).getLabel());
		Assertions.assertEquals("item 5", listcells.get(1).as(Listcell.class).getLabel());
		Assertions.assertEquals("item 6", listcells.get(2).as(Listcell.class).getLabel());
	}
}

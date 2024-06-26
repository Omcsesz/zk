/* B80_ZK_1696_treeTest.java

	Purpose:
		
	Description:
		
	History:
		Tue Aug 11 16:59:04 CST 2015, Created by Christopher

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
import org.zkoss.zats.mimic.operation.OpenAgent;
import org.zkoss.zats.mimic.operation.PagingAgent;
import org.zkoss.zktest.zats.ZATSTestCase;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.ext.Pageable;

/**
 * 
 * @author Christopher
 */
public class B80_ZK_1696_treeTest extends ZATSTestCase {
	@Test
	public void noOpenTest(){
		DesktopAgent desktop = connect();
		ComponentAgent treeAgent = desktop.query("#tree");
		Tree mytree = treeAgent.as(Tree.class);
		Assertions.assertNotNull(mytree);
		//check default render result
		Assertions.assertEquals(0, mytree.getActivePage());
		List<ComponentAgent> treeCells = treeAgent.queryAll("treecell");
		Assertions.assertEquals("item 1", treeCells.get(0).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 2", treeCells.get(1).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 3", treeCells.get(2).as(Treecell.class).getLabel());
		
		//switch to 2nd page
		PagingAgent paging = desktop.query("tree > paging").as(PagingAgent.class);
		paging.moveTo(1);
		//check that render result after switching page
		Assertions.assertEquals(1, mytree.getActivePage());
		treeCells = treeAgent.queryAll("treecell");
		Assertions.assertEquals("item 4", treeCells.get(0).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 5", treeCells.get(1).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 6", treeCells.get(2).as(Treecell.class).getLabel());
		
		desktop.query("#btn4").click(); //switch to 2nd model
		//check render result after changing model
		Assertions.assertEquals(1, mytree.getActivePage());
		treeCells = treeAgent.queryAll("treecell");
		Assertions.assertEquals("item a", treeCells.get(0).as(Treecell.class).getLabel());
		Assertions.assertEquals("item b", treeCells.get(1).as(Treecell.class).getLabel());
		Assertions.assertEquals("item c", treeCells.get(2).as(Treecell.class).getLabel());
		
		desktop.query("#btn1").click(); //switch back to 1st model
		//check render result after switching back to original model
		Assertions.assertEquals(1, mytree.getActivePage());
		treeCells = treeAgent.queryAll("treecell");
		Assertions.assertEquals("item 4", treeCells.get(3).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 5", treeCells.get(4).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 6", treeCells.get(5).as(Treecell.class).getLabel());
	}
	
	@Test
	public void openFrontTest(){
		//this test opens the 2nd node
		DesktopAgent desktop = connect();
		ComponentAgent treeAgent = desktop.query("#tree");
		Tree mytree = treeAgent.as(Tree.class);
		Assertions.assertNotNull(mytree);
		Assertions.assertEquals(0, mytree.getActivePage());
		
		//check item count of closed node
		ComponentAgent secondItem = treeAgent.queryAll("treeitem").get(1);
		Assertions.assertEquals(1, secondItem.as(Treeitem.class).getVisibleItemCount());
		Assertions.assertEquals(2, mytree.getPageCount());
		secondItem.as(OpenAgent.class).open(true);
		//check item count of opened node
		Assertions.assertEquals(7, secondItem.as(Treeitem.class).getVisibleItemCount());
		Assertions.assertEquals(4, mytree.getPageCount());
		
		//change page
		PagingAgent paging = desktop.query("tree > paging").as(PagingAgent.class);
		paging.moveTo(1);
		Assertions.assertEquals(1, mytree.getActivePage());
		//check render result after changing page
		List<ComponentAgent> treeCells = treeAgent.queryAll("treecell");
		Assertions.assertEquals("item 22", treeCells.get(1).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 23", treeCells.get(2).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 24", treeCells.get(3).as(Treecell.class).getLabel());
		
		//change model
		desktop.query("#btn4").click(); //switch to 2nd model
		Assertions.assertEquals(1, mytree.getActivePage());
		//check render result after changing model
		treeCells = treeAgent.queryAll("treecell");
		Assertions.assertEquals("item a", treeCells.get(0).as(Treecell.class).getLabel());
		Assertions.assertEquals("item b", treeCells.get(1).as(Treecell.class).getLabel());
		Assertions.assertEquals("item c", treeCells.get(2).as(Treecell.class).getLabel());
		
		//switch back to original model
		desktop.query("#btn1").click(); //switch back to 1st model
		Assertions.assertEquals(1, mytree.getActivePage());
		//check render result after switching back to original model
		treeCells = treeAgent.queryAll("treecell");
		Assertions.assertEquals("item 22", treeCells.get(3).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 23", treeCells.get(4).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 24", treeCells.get(5).as(Treecell.class).getLabel());
	}
	
	@Test
	public void openBackTest(){
		//this test opens the last node
		DesktopAgent desktop = connect();
		ComponentAgent treeAgent = desktop.query("#tree");
		Tree mytree = treeAgent.as(Tree.class);
		Assertions.assertNotNull(mytree);
		Assertions.assertEquals(0, mytree.getActivePage());
		
		//check item count before node open
		ComponentAgent lastItem = treeAgent.queryAll("treeitem").get(5);
		Assertions.assertEquals(1, lastItem.as(Treeitem.class).getVisibleItemCount());
		Assertions.assertEquals(2, mytree.getPageCount());
		lastItem.as(OpenAgent.class).open(true);
		//check item count after node open
		Assertions.assertEquals(7, lastItem.as(Treeitem.class).getVisibleItemCount());
		Assertions.assertEquals(4, mytree.getPageCount());
		
		//move to last page
		PagingAgent paging = desktop.query("tree > paging").as(PagingAgent.class);
		paging.moveTo(3);
		Assertions.assertEquals(3, mytree.getActivePage());
		//check render result after moving to last page
		List<ComponentAgent> treeCells = treeAgent.queryAll("treecell");
		Assertions.assertEquals("item 64", treeCells.get(6).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 65", treeCells.get(7).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 66", treeCells.get(8).as(Treecell.class).getLabel());
		
		//switch to 2nd model
		desktop.query("#btn4").click(); //switch to 2nd model
		Assertions.assertEquals(1, mytree.getActivePage());
		//check render result after switching to 2nd model
		treeCells = treeAgent.queryAll("treecell");
		Assertions.assertEquals("item a", treeCells.get(0).as(Treecell.class).getLabel());
		Assertions.assertEquals("item b", treeCells.get(1).as(Treecell.class).getLabel());
		Assertions.assertEquals("item c", treeCells.get(2).as(Treecell.class).getLabel());
		
		//switch back to original model
		desktop.query("#btn1").click(); //switch back to 1st model
		Assertions.assertEquals(3, mytree.getActivePage());
		//check render result after switching back to original model
		treeCells = treeAgent.queryAll("treecell");
		Assertions.assertEquals("item 64", treeCells.get(7).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 65", treeCells.get(8).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 66", treeCells.get(9).as(Treecell.class).getLabel());
	}
	
	@Test
	public void apiTest(){
		//this test opens the 2nd node from api
		DesktopAgent desktop = connect();
		ComponentAgent treeAgent = desktop.query("#tree");
		Tree mytree = treeAgent.as(Tree.class);
		Assertions.assertNotNull(mytree);
		Assertions.assertEquals(0, mytree.getActivePage());
		
		//switch to 2nd model
		desktop.query("#btn4").click();
		Assertions.assertEquals(0, mytree.getActivePage());
		//check render result after switching to 2nd model
		List<ComponentAgent> treeCells = treeAgent.queryAll("treecell");
		Assertions.assertEquals("item a", treeCells.get(0).as(Treecell.class).getLabel());
		Assertions.assertEquals("item b", treeCells.get(1).as(Treecell.class).getLabel());
		Assertions.assertEquals("item c", treeCells.get(2).as(Treecell.class).getLabel());
		
		//switch to 1st model, open 2nd node, and switch to 2nd page
		desktop.query("#btn2").click();
		Assertions.assertEquals(1, mytree.getActivePage());
		//check render result after moving to 2nd page
		treeCells = treeAgent.queryAll("treecell");
		Assertions.assertEquals("item 22", treeCells.get(1).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 23", treeCells.get(2).as(Treecell.class).getLabel());
		Assertions.assertEquals("item 24", treeCells.get(3).as(Treecell.class).getLabel());
	}
	
	@Test
	public void testModelPageCount(){
		//this test checks the value of page count is in sync between model and tree view
		DesktopAgent desktop = connect();
		ComponentAgent treeAgent = desktop.query("#tree");
		Tree mytree = treeAgent.as(Tree.class);
		Assertions.assertNotNull(mytree);
		//checks init page count is in sync
		Assertions.assertEquals(2, mytree.getPageCount());
		Assertions.assertEquals(2, ((Pageable) mytree.getModel()).getPageCount());
		
		ComponentAgent secondItem = treeAgent.queryAll("treeitem").get(1);
		secondItem.as(OpenAgent.class).open(true);
		
		// after opening 2nd node, should have 4 pages now
		Assertions.assertEquals(4, mytree.getPageCount());
		Assertions.assertEquals(4, ((Pageable) mytree.getModel()).getPageCount());
		
		//switch to 2nd model
		desktop.query("#btn4").click();
		Assertions.assertEquals(2, mytree.getPageCount());
		Assertions.assertEquals(2, ((Pageable) mytree.getModel()).getPageCount());
		
		//switch to 1st model
		desktop.query("#btn1").click();
		Assertions.assertEquals(4, mytree.getPageCount());
		Assertions.assertEquals(4, ((Pageable) mytree.getModel()).getPageCount());
	}
}

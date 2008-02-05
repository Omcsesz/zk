<%--
listbox.dsp

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Wed Jun 15 18:20:53     2005, Created by tomyeh
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
--%><%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %>
<%@ taglib uri="http://www.zkoss.org/dsp/zul/core" prefix="zu" %>
<c:set var="self" value="${requestScope.arg.self}"/>
<div id="${self.uuid}" z.type="zul.sel.Libox"${self.outerAttrs}${self.innerAttrs}>
<c:if test="${!empty self.listhead}">
	<div id="${self.uuid}!head" class="listbox-head">
	<table width="${self.innerWidth}" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed">
	<c:forEach var="head" items="${self.heads}">
${z:redraw(head, null)}
	</c:forEach>
	</table>
	</div>
</c:if>
<c:set var="hgh" if="${self.rows > 1}" value="style=\"overflow:hidden;height:${self.rows * 15}px\""/>
<c:set var="hgh" if="${!empty self.height}" value="style=\"overflow:hidden;height:${self.height}\""/>
	<div id="${self.uuid}!body" class="listbox-body" ${hgh}>
	<table width="${self.innerWidth}" border="0" cellpadding="0" cellspacing="0" id="${self.uuid}!cave" class="listbox-btable">
${zu:resetStripeClass(self)}
	<c:forEach var="item" items="${self.items}">
${zu:setStripeClass(item)}	
${z:redraw(item, null)}
	</c:forEach>
	</table>
	</div>
<c:if test="${!empty self.listfoot}">
	<div id="${self.uuid}!foot" class="listbox-foot">
	<table width="${self.innerWidth}" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed">
${z:redraw(self.listfoot, null)}
	</table>
	</div>
</c:if>
</div>

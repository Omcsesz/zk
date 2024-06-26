/* listfoot.js

	Purpose:
		
	Description:
		
	History:
		Tue Jun  9 18:03:31     2009, Created by jumperchen

Copyright (C) 2009 Potix Corporation. All Rights Reserved.

This program is distributed under LGPL Version 2.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
function listfoot$mold$(out) {
	out.push('<tr', this.domAttrs_(), '>');
	for (var w = this.firstChild; w; w = w.nextSibling)
		w.redraw(out);
	
	var listbox = this.getListbox();
	if (listbox._nativebar)
		out.push('<td class="', this.$s('bar'), '" ></td>');
	
	out.push('</tr>');
}

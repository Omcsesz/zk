/* radiogroup.js

	Purpose:
		
	Description:
		
	History:
		Wed Dec 17 09:32:45     2008, Created by jumperchen

Copyright (C) 2008 Potix Corporation. All Rights Reserved.

This program is distributed under LGPL Version 2.1 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
function radiogroup$mold$(out) {
	out.push('<span', this.domAttrs_(), ' role="radiogroup">');
	for (var w = this.firstChild; w; w = w.nextSibling)
		w.redraw(out);
	out.push('</span>');
}
/* create.js

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Sat Oct 18 19:24:38     2008, Created by tomyeh
}}IS_NOTE

Copyright (C) 2008 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
/** Begins the creating of widgets and pages. */
function zkcrbg() {
	zk.creating = true;
}
/** Ends the creating of widgets and pages. */
function zkcre() {
	if (!zk.loading) {
		zk.booted = true;
		zk.creating = false;
	}
	_zkws = []; //clean up if failed
	zkcurdt = null;
}

/** Used internally. */
function _zkbeg(w) {
	w.children = [];
	if (_zkws.length > 0)
		_zkws[0].children.add(w);
	_zkws.unshift(w);
}
/** Used internally. */
function _zkend() {
	var w = _zkws.shift();
	if (!_zkws.length) {
		_zkld(w);

		_zkcrinf = [zkcurdt, w];
		zkPkg.addAfterLoad(_zkafld);
	}
}
/** Used internally. */
function _zkafld() {
	if (_zkcrinf) {
		var dt = _zkcrinf[0], wginf = _zkcrinf[1];
		_zkcrinf = null;

		var wgt = _zkcreate(null, wginf);
		zkDom.setOuterHTML(zkDom.$(wgt.uuid), wgt.redraw());
	}
}
/** Used internally to create the widget tree. */
function _zkcreate(parent, wginf) {
	var wgt;
	if (wginf.type == "#p") {
		wgt = new zk.Page(wginf.uuid, wginf.contained);
		if (wginf.style) wgt.style = wginf.style;
		if (parent) parent.appendChild(wgt);
	} else {
		var cls = zk.$import(wginf.type),
			uuid = wginf.uuid, props = wginf.props,
			wgt = new cls(uuid, wginf.mold),
			embedAs = cls.embedAs;
		wgt.inServer = true;
		if (parent) parent.appendChild(wgt);

		//embedAs means value from element's text
		if (embedAs && !props[embedAs]) {
			var embed = zkDom.$(uuid);
			if (embed)
				props[embedAs] = embed.innerHTML;
		}

		for (var p in props) {
			var m = wgt['set' + p.charAt(0).toUpperCase() + p.substring(1)];
			if (m) m(props[p]);
			else wgt[p] = props[p];
		}
	}

	for (var j = 0, childs = wginf.children, len = childs.length;
	j < len; ++j)
		_zkcreate(wgt, childs[j]);

	return wgt;
}
/** Used internally to load package of the specified widget/page. */
function _zkld(w) {
	var type = w.type; j = type.lastIndexOf('.');
	if (j >= 0)
		zkPkg.load(type.substring(0, j), zkcurdt);
	for (var children = w.children, len = children.length, j = 0; j < len;++j)
		_zkld(children[j]);
}

/** Used internally. */
var _zkws = [], zkcurdt; //used to load widget

/** Begins the creation of a page generated by the server.
 *
 * @param contained if a page is not owned by another page, and
 * it doesn't cover the whole browser window (included by non-ZK tech)
 */
function zkpgbg(pgid, style, dtid, contained, updateURI) {
	if (dtid)
		zkdtbg(dtid, updateURI).pgid = pgid;

	_zkbeg({type: "#p", uuid: pgid,
		style: style ? style: "height:100%;width:100%",
		contained: contained});
}
/** Ends the creation of a page.
 */
zkpge = _zkend;

/** Begins the creation of a widget generated by the server.
 */
function zkbg(type, uuid, mold, props) {
	_zkbeg({type: type, uuid: uuid, mold: mold ? mold: "default",
		props: props});
}
/** Ends the creation of a widget. */
zke = _zkend;

/** Begins the creation of a desktop generated by the server.
 * This method is called only if zkpgbg is not called.
 * <p>Note: there is no zken().
 */
function zkdtbg(dtid, updateURI) {
	var dt = zk.Desktop.of(dtid);
	if (dt == null) dt = new zk.Desktop(dtid, updateURI);
	else if (updateURI) dt.updateURI = updateURI;
	zkcurdt = dt;
	return dt;
}

//Init Only//
/** Sets the version. */
function zkver() {
	var args = arguments, len = args.length;
	zk.version = args[0];
	zk.build = args[1];

	for (var j = 2; j < len; j += 2)
		zkPkg.version(args[j], args[j + 1]);
	return;
}

/** Sets the options. */
function zkopt(opts) {
	for (var nm in opts) {
		var val = opts[nm];
		switch (nm) {
		case "pd": zk.procDelay = val; break;
		case "td": zk.tipDelay =  val; break;
		case "rd": zk.resendDelay = val; break;
		case "dj": zk.debugJS = val; break;
		case "kd": zk.keepDesktop = val; break;
		case "pf": zk.pfmeter = val; break;
		}
	}
}

/* Iframe.java

	Purpose:
		
	Description:
		
	History:
		Tue Dec 13 15:02:12     2005, Created by tomyeh

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under LGPL Version 2.1 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zhtml;

import org.zkoss.zhtml.impl.AbstractTag;
import org.zkoss.zk.ui.WrongValueException;

/**
 * The IFRAME tag.
 * 
 * @author tomyeh
 */
public class Iframe extends AbstractTag {
	public Iframe() {
		super("iframe");
	}
	/**
	 * Returns the height of this iframe tag.
	 * @since 8.0.3
	 */
	public String getHeight() {
		return (String) getDynamicProperty("height");
	}

	/**
	 * Sets the height of this iframe tag.
	 * @since 8.0.3
	 */
	public void setHeight(String height) throws WrongValueException {
		setDynamicProperty("height", height);
	}
	/**
	 * Returns the name of this iframe tag.
	 * @since 8.0.3
	 */
	public String getName() {
		return (String) getDynamicProperty("name");
	}

	/**
	 * Sets the name of this iframe tag.
	 * @since 8.0.3
	 */
	public void setName(String name) throws WrongValueException {
		setDynamicProperty("name", name);
	}
	/**
	 * Returns the sandbox of this iframe tag.
	 * <p>Notice that this attribute refers to the corresponding attribute of the HTML5 specification.
	 * Hence, it would still be rendered to client-side as a DOM attribute even if the browser doesn’t support it.
	 * @since 8.0.3
	 */
	public String getSandbox() {
		return (String) getDynamicProperty("sandbox");
	}

	/**
	 * Sets the sandbox of this iframe tag.
	 * <p>Notice that this attribute refers to the corresponding attribute of the HTML5 specification.
	 * Hence, it would still be rendered to client-side as a DOM attribute even if the browser doesn’t support it.
	 * @since 8.0.3
	 */
	public void setSandbox(String sandbox) throws WrongValueException {
		setDynamicProperty("sandbox", sandbox);
	}
	/**
	 * Returns the src of this iframe tag.
	 * @since 8.0.3
	 */
	public String getSrc() {
		return (String) getDynamicProperty("src");
	}

	/**
	 * Sets the src of this iframe tag.
	 * @since 8.0.3
	 */
	public void setSrc(String src) throws WrongValueException {
		setDynamicProperty("src", src);
	}
	/**
	 * Returns the srcdoc of this iframe tag.
	 * <p>Notice that this attribute refers to the corresponding attribute of the HTML5 specification.
	 * Hence, it would still be rendered to client-side as a DOM attribute even if the browser doesn’t support it.
	 * @since 8.0.3
	 */
	public String getSrcdoc() {
		return (String) getDynamicProperty("srcdoc");
	}

	/**
	 * Sets the srcdoc of this iframe tag.
	 * <p>Notice that this attribute refers to the corresponding attribute of the HTML5 specification.
	 * Hence, it would still be rendered to client-side as a DOM attribute even if the browser doesn’t support it.
	 * @since 8.0.3
	 */
	public void setSrcdoc(String srcdoc) throws WrongValueException {
		setDynamicProperty("srcdoc", srcdoc);
	}
	/**
	 * Returns the width of this iframe tag.
	 * @since 8.0.3
	 */
	public String getWidth() {
		return (String) getDynamicProperty("width");
	}

	/**
	 * Sets the width of this iframe tag.
	 * @since 8.0.3
	 */
	public void setWidth(String width) throws WrongValueException {
		setDynamicProperty("width", width);
	}

	/**
	 * Returns the allow of this tag.
	 *
	 * @since 10.0.0
	 */
	public String getAllow() {
		return (String) getDynamicProperty("allow");
	}

	/**
	 * Sets the allow of this tag.
	 *
	 * @since 10.0.0
	 */
	public void setAllow(String allow) throws WrongValueException {
		setDynamicProperty("allow", allow);
	}

	/**
	 * Returns the allowfullscreen of this tag.
	 *
	 * @since 10.0.0
	 */
	public String getAllowfullscreen() {
		return (String) getDynamicProperty("allowfullscreen");
	}

	/**
	 * Sets the allowfullscreen of this tag.
	 *
	 * @since 10.0.0
	 */
	public void setAllowfullscreen(String allowfullscreen) throws WrongValueException {
		setDynamicProperty("allowfullscreen", allowfullscreen);
	}

	/**
	 * Returns the loading of this tag.
	 *
	 * @since 10.0.0
	 */
	public String getLoading() {
		return (String) getDynamicProperty("loading");
	}

	/**
	 * Sets the loading of this tag.
	 *
	 * @since 10.0.0
	 */
	public void setLoading(String loading) throws WrongValueException {
		setDynamicProperty("loading", loading);
	}

	/**
	 * Returns the referrerpolicy of this tag.
	 *
	 * @since 10.0.0
	 */
	public String getReferrerpolicy() {
		return (String) getDynamicProperty("referrerpolicy");
	}

	/**
	 * Sets the referrerpolicy of this tag.
	 *
	 * @since 10.0.0
	 */
	public void setReferrerpolicy(String referrerpolicy) throws WrongValueException {
		setDynamicProperty("referrerpolicy", referrerpolicy);
	}
}

/* DefaultIDOMFactory.java


	Purpose: 
	Description: 
	History:
	2001/10/25 12:00:50, Create, Tom M. Yeh.

Copyright (C) 2001 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under LGPL Version 2.1 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.idom.input;

import org.zkoss.idom.Attribute;
import org.zkoss.idom.CData;
import org.zkoss.idom.Comment;
import org.zkoss.idom.DocType;
import org.zkoss.idom.Document;
import org.zkoss.idom.Element;
import org.zkoss.idom.EntityReference;
import org.zkoss.idom.Namespace;
import org.zkoss.idom.ProcessingInstruction;
import org.zkoss.idom.Text;

/**
 * The default iDOM factory.
 *
 * @author tomyeh
 */
public class DefaultIDOMFactory implements IDOMFactory {
	/** Constructor.
	 */
	public DefaultIDOMFactory() {
	}

	//-- IDOMFactory --//
	public Attribute newAttribute(String lname, String value) {
		return new Attribute(lname, value);
	}
	public Attribute newAttribute(Namespace ns, String lname, String value) {
		return new Attribute(ns, lname, value);
	}
	public CData newCData(String text) {
		return new CData(text);
	}
	public Comment newComment(String text) {
		return new Comment(text);
	}
	public DocType
	newDocType(String elementName, String publicId, String systemId) {
		return new DocType(elementName, publicId, systemId);
	}
	public Document newDocument(Element rootElement, DocType docType) {
		return new Document(rootElement, docType);
	}
	public Element newElement(Namespace ns, String lname) {
		return new Element(ns, lname);
	}
	public Element newElement(String lname) {
		return new Element(lname);
	}
	public ProcessingInstruction newProcessingInstruction(String target, String data) {
		return new ProcessingInstruction(target, data);
	}
	public EntityReference newEntityRef(String name) {
		return new EntityReference(name);
	}
	public Text newText(String text) {
		return new Text(text);
	}
}

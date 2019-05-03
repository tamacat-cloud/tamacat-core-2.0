/*
 * Copyright (c) 2014 tamacat.org
 */
package cloud.tamacat.di.define.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="property")
public class Prop {

	@XmlAttribute(required=true)
	String name;

	@XmlAttribute
	String mode;

	@XmlElement
	String value;

	@XmlElement
	Ref ref;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRefId() {
		if (ref != null) {
			return ref.getBean();
		} else {
			return null;
		}
	}

	public Ref getRef() {
		return ref;
	}

	public void setRef(Ref ref) {
		this.ref = ref;
	}
}

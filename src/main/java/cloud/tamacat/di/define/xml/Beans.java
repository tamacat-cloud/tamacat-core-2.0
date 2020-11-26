/*
 * Copyright 2014 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 
 */
package cloud.tamacat.di.define.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="beans")
public class Beans {

	@XmlElement(name="bean")
	List<Bean> beans = new ArrayList<>();

	public List<Bean> getBeans() {
		return beans;
	}

	public void addBean(Bean bean) {
		this.beans.add(bean);
	}
}

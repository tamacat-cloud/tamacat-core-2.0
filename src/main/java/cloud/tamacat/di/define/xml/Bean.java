/*
 * Copyright (c) 2014 tamacat.org
 */
package cloud.tamacat.di.define.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id", "name", "className", "initMethod", "factoryMethod", "scope", "constructorArgs", "props"})
public class Bean {

	@XmlAttribute(required=true)
	String id;

	@XmlAttribute
	String name;

	@XmlAttribute(name="class", required=true)
	String className;

	@XmlAttribute(name="init-method")
	String initMethod;

	@XmlAttribute(name="factory-method")
	String factoryMethod;

	@XmlAttribute
	String scope;

	@XmlElement(name="constructor-arg")
	List<ConstructorArg> constructorArgs = new ArrayList<>();

	@XmlElement(name="property")
	List<Prop> props = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getInitMethod() {
		return initMethod;
	}

	public void setInitMethod(String initMethod) {
		this.initMethod = initMethod;
	}

	public String getFactoryMethod() {
		return factoryMethod;
	}

	public void setFactoryMethod(String factoryMethod) {
		this.factoryMethod = factoryMethod;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public List<ConstructorArg> getConstructorArgs() {
		return constructorArgs;
	}

	public void setConstructorArgs(List<ConstructorArg> constructorArgs) {
		this.constructorArgs = constructorArgs;
	}

	public void addConstructorArg(ConstructorArg constructorArg) {
		this.constructorArgs.add(constructorArg);
	}

	public List<Prop> getProperties() {
		return props;
	}

	public void setProperties(List<Prop> props) {
		this.props = props;
	}

	public void addProperty(Prop prop) {
		this.props.add(prop);
	}

	public void addProperty(String name, String value) {
		Prop prop = new Prop();
		prop.setName(name);
		prop.setValue(value);
		this.props.add(prop);
	}

	public void addProperty(String name, Bean bean) {
		Ref ref = new Ref();
		ref.setBean(bean.getId());
		Prop prop = new Prop();
		prop.setRef(ref);
		this.props.add(prop);
	}
}

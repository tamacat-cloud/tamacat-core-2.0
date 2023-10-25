open module tamacat.core {
	
	exports cloud.tamacat.util;
	exports cloud.tamacat;
	exports cloud.tamacat.log;
	exports cloud.tamacat.di;
	exports cloud.tamacat.di.define;

	exports cloud.tamacat.log.impl;
	exports cloud.tamacat.di.define.xml;
	exports cloud.tamacat.di.impl;
	
	requires transitive jakarta.xml.bind;
	requires transitive com.google.gson;
	requires transitive java.logging;
	requires transitive org.slf4j;
}
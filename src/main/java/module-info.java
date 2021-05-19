module cloud.tamacat.core {
	exports cloud.tamacat.util;
	exports cloud.tamacat;
	exports cloud.tamacat.log;
	exports cloud.tamacat.di;
	exports cloud.tamacat.di.define;

	exports cloud.tamacat.log.impl;
	exports cloud.tamacat.di.define.xml;
	exports cloud.tamacat.di.impl;
	
	opens cloud.tamacat.log.impl;
	opens cloud.tamacat.di;
	opens cloud.tamacat.di.define;
	opens cloud.tamacat.di.define.xml;
	opens cloud.tamacat.di.impl;
	opens cloud.tamacat.util;
	
	//requires java.desktop;
	requires java.xml;
	//requires java.xml.bind;
	requires jakarta.xml.bind;
	//requires transitive java.json;
	
	requires transitive com.google.gson;
	requires transitive java.logging;
	requires transitive org.slf4j;
}
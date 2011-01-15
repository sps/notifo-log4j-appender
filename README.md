notifo log4j appender
-------------

	<dependency>
		<groupId>com.github.sps.notifo4j</groupId>
		<artifactId>log4j-appender</artifactId>
		<version>1.0</version>
	</dependency>
	
###sample log4j.properties###

	log4j.rootCategory=debug, stdout, notifo
	# quiet down http commons client.
	log4j.category.org.apache.http=info
	# simple console appender
	log4j.appender.stdout=org.apache.log4j.ConsoleAppender
	log4j.appender.stdout.layout=org.apache.log4j.TTCCLayout
	# Notifo appender config
	log4j.appender.notifo=com.notifo.log4j.NotifoLog4jAppender
	# recommended to set the threshold pretty high or you will probably flood yourself.
	# warning: it is possible to create a recursion/stack overflow if org.apache.http is logging at or above  
	# the threshold level as this appender.
	log4j.appender.notifo.threshold=FATAL
	log4j.appender.notifo.userName=USERNAME
	log4j.appender.notifo.apiKey=APIKEY


###sample log4j.xml###
	<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

	<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
	    <appender name="console" class="org.apache.log4j.ConsoleAppender"> 
	        <param name="Threshold" value="${CONSOLE_LOGGING_THRESHOLD}"/> 
	        <param name="Target" value="System.out"/> 
	        <layout class="org.apache.log4j.PatternLayout"> 
	            <!-- Include [%c] to see full category for figuring out how to turn down logging -->
	            <param name="ConversionPattern" value="%d{ISO8601} %-5p [%F:%L] %m%n"/> 
	        </layout> 
	    </appender> 

	    <appender name="notifo" class="com.notifo.log4j.NotifoLog4jAppender"> 
	        <param name="threshold" value="FATAL"/> 
	        <param name="userName" value="${NOTIFO_USERNAME}"/> 
	        <param name="apiKey" value="${NOTIFO_APIKEY}"/> 
	    </appender> 
	    <root> 
	        <appender-ref ref="console" /> 
	        <appender-ref ref="notifo" /> 
	    </root>
	</log4j:configuration>


###how to build##
For the non-maven types, here are the required dependencies

* [notifo4j](http://github.com/sps/notifo4j/downloads)
* [gson 1.4](http://code.google.com/p/google-gson/downloads/list)
* [apache commons httpclient 4.0.1](http://hc.apache.org/downloads.cgi)
* [commons-logging 1.1.1](http://commons.apache.org/logging/download_logging.cgi)
* [commons-codec (commons-logging dependency)](http://commons.apache.org/codec/download_codec.cgi)

That should be all you need.

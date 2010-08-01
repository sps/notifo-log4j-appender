notifo log4j appender
-------------

###sample log4j.properties###
<pre>
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
</pre>
 

###how to build##
until I can get some artifacts published to a public repository, you'll need to install the 
[notifo java client](http://github.com/sps/notifo4j) client locally. other than that, its a standard mvn install.

for the non-maven types, here are the required dependencies

* [notifo4j](http://github.com/sps/notifo4j/downloads)
* [gson 1.4](http://code.google.com/p/google-gson/downloads/list)
* [apache commons httpclient 4.0.1](http://hc.apache.org/downloads.cgi)
* [commons-logging 1.1.1](http://commons.apache.org/logging/download_logging.cgi)
* [commons-codec (commons-logging dependency)](http://commons.apache.org/codec/download_codec.cgi)

That should be all you need.

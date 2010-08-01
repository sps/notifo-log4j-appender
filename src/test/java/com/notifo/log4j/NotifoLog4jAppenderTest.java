/**
 * Copyright (C) 2010 Sean P Scanlon.
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package com.notifo.log4j;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.notifo.client.NotifoClient;

/**
 * @author sscanlon
 * 
 */
public class NotifoLog4jAppenderTest {

    private Mockery mockery;
    private NotifoClient client;
    private NotifoLog4jAppender appender;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();
        client = mockery.mock(NotifoClient.class);
        appender = new NotifoLog4jAppender();
        appender.setApiKey(null);
        appender.setUserName(null);
        appender.setThreshold(Level.ERROR);
        appender.setClient(client);
    }

    @Test
    public void testBasic() {
        appender.doInit();
        assertFalse(appender.requiresLayout());
        assertTrue(appender.isInitialized());
        appender.close();
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testAppendLoggingEvent() throws Exception {
        LoggingEvent event = new LoggingEvent("com.foo.Class",
                null,
                100L,
                Level.TRACE,
                "this is my messaage",
                null,
                null,
                null,
                null,
                null);
        mockery.checking(new Expectations() {
            {
                one(client).sendMessage(with(any(String.class)));
                will(returnValue(null));

            }
        });
        appender.append(event);
        event.level = Level.FATAL;
        appender.append(event);
        mockery.assertIsSatisfied();
    }

}

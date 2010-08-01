/**
 * Copyright (C) 2010 Sean P. Scanlon 
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package com.notifo.log4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.notifo.client.NotifoClient;
import com.notifo.client.NotifoException;
import com.notifo.client.NotifoHttpClient;

/**
 * @author sscanlon
 * 
 */
public class NotifoLog4jAppender extends AppenderSkeleton {

    private String userName;
    private String apiKey;
    private NotifoClient client;
    private boolean didInit;

    public void close() {
    }

    public boolean requiresLayout() {
        return false;
    }

    protected void append(LoggingEvent event) {

        if (!isAsSevereAsThreshold(event.getLevel())) {
            return;
        }

        if (!isInitialized()) {
            doInit();
        }

        try {
            client.sendMessage(event.getRenderedMessage());
        } catch (NotifoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doInit() {
        if (didInit) {
            return;
        }
        if (client == null) {
            client = new NotifoHttpClient(this.userName, this.apiKey);
            didInit = true;
        }
    }

    protected boolean isInitialized() {
        return this.client != null && didInit;
    }

    /**
     * @param userName
     *        the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @param apiKey
     *        the apiKey to set
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setClient(NotifoClient client) {
        didInit = true;
        this.client = client;
    }

}

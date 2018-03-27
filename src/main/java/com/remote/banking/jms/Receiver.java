package com.remote.banking.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private final static Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(final String personDTO) {
        LOGGER.info("Received <" + personDTO + ">");
    }

}

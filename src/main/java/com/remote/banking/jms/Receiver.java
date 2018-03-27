package com.remote.banking.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.remote.banking.controllers.JMSController.DESTINATION_NAME;

/**
 * message driven POJO
 */
@Component
public class Receiver {

    private final static Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @JmsListener(destination = DESTINATION_NAME, containerFactory = "myFactory")
    public void receiveMessage(final String personDTO) {
        LOGGER.info("Received <" + personDTO + ">");
    }

}

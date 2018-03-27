package com.remote.banking.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(String personDTO) {
        System.out.println("Received <" + personDTO + ">");
    }

}

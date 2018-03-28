package com.remote.banking.controllers;

import com.remote.banking.models.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.remote.banking.app.SpringBootRestApplication.PRETTY_PRINTING_BUILDER;

/**
 * Two beans that we don’t see defined are JmsTemplate and ConnectionFactory.
 * These are created automatically by Spring Boot.
 * In this case, the ActiveMQ broker runs embedded.
 * <p>
 * By default, Spring Boot creates a JmsTemplate configured to transmit to queues by having pubSubDomain set to false.
 * The JmsMessageListenerContainer is also configured the same.
 * To override, set spring.jms.isPubSubDomain=true via Boot’s property settings
 * (either inside application.properties or by environment variable).
 * Then make sure the receiving container has the same setting.
 */
@RestController
@RequestMapping("${api.root}/send-to-jms-queue")
public class JMSController {

    private final static Logger LOGGER = LoggerFactory.getLogger(JMSController.class);

    public final static String DESTINATION_NAME = "mailbox";

    @Autowired
    ConfigurableApplicationContext configurableApplicationContext;

    @RequestMapping(method = RequestMethod.GET, value = "/fake-user")
    public ResponseEntity<String> sendFakeUserToJMSQueue() {
        // Send a message with a POJO - the template reuse the message converter
        LOGGER.info("Sending an email message....");
        final JmsTemplate jmsTemplate = configurableApplicationContext.getBean(JmsTemplate.class);
        final PersonDTO personDTO =
                new PersonDTO("firstName1-fake-user", "lastName-fake-user",
                        LocalDate.now(), "fake-user", "fake-user");
        final String toJson = PRETTY_PRINTING_BUILDER.create().toJson(personDTO);

        jmsTemplate.convertAndSend(DESTINATION_NAME, toJson);
        final String email_has_been_sent = "Email has been sent";
        LOGGER.info(email_has_been_sent);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(email_has_been_sent);
    }
}

package com.remote.banking.controllers;

import com.remote.banking.models.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.ConnectionFactory;
import java.time.LocalDate;

import static com.remote.banking.app.SpringBootRestApplication.PRETTY_PRINTING_BUILDER;

@RestController
@RequestMapping("${api.root}/send-to-jms-queue")
public class JMSController {

    private final static Logger LOGGER = LoggerFactory.getLogger(JMSController.class);

    @Autowired
    ConfigurableApplicationContext configurableApplicationContext;


    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fake-user")
    public ResponseEntity<String> sendFakeUserToJMSQueue() {
        // Send a message with a POJO - the template reuse the message converter
        LOGGER.info("Sending an email message....");
        final JmsTemplate jmsTemplate = configurableApplicationContext.getBean(JmsTemplate.class);
        final PersonDTO personDTO =
                new PersonDTO("firstName1-fake-user", "lastName-fake-user",
                        LocalDate.now(), "fake-user", "fake-user");
        final String toJson = PRETTY_PRINTING_BUILDER.create().toJson(personDTO);
        jmsTemplate.convertAndSend("mailbox", toJson);
        final String email_has_been_sent = "Email has been sent";
        LOGGER.info(email_has_been_sent);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(email_has_been_sent);
    }
}

package com.remote.banking.controllers;

import com.remote.banking.models.dao.UserDAO;
import com.remote.banking.models.for_rdbms.Emails;
import com.remote.banking.models.for_rdbms.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.root}")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ResponseEntity<String> getUserInfo() {
        LOGGER.info("Start retrieve all persons from database");
        final List<Person> all = userDAO.findAllPersons();
        LOGGER.info("All persons from database have been retrieved");
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(all.toString());
    }


    @RequestMapping(method = RequestMethod.GET, value = "/users/{idperson}")
    public ResponseEntity<String> getUserInfo(@PathVariable int idperson) {
        LOGGER.info("Start retrieve person with id {} from database", idperson);
        final Person byId = userDAO.findById(idperson);
        LOGGER.info("Person {} retrieved from database", byId);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(byId.toString());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{idperson}/emails")
    public ResponseEntity<String> getUserEmails(@PathVariable int idperson) {
        LOGGER.info("Start retrieve all emails for person with id {} from database", idperson);
        final Person byId = userDAO.findById(idperson);
        List<Emails> emailsList = byId.getEmailsList();
        LOGGER.info("Emails with size {} retrieved from database", emailsList.size());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(emailsList.toString());
    }
}

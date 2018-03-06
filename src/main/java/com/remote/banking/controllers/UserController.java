package com.remote.banking.controllers;

import com.google.gson.GsonBuilder;
import com.remote.banking.models.dao.UserDAO;
import com.remote.banking.models.dto.PersonDTO;
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

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("${api.root}")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDAO userDAO;

    private String formBaseRESTFulURLForUsers() {
        return "http://localhost:8080/rest/v1" + "/users";
    }

    private String formBaseRESTFulURLForUsersEmails(final int idperson) {
        return formBaseRESTFulURLForUsers() + "/" + idperson + "/emails";
    }

    private static final GsonBuilder PRETTY_PRINTING_BUILDER = new GsonBuilder().setPrettyPrinting();

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ResponseEntity<String> getUserInfo() {
        LOGGER.info("Start retrieve all persons from database");
        final List<Person> all = userDAO.findAllPersons();
        LOGGER.info("All persons from database have been retrieved");
        List<PersonDTO> personDTOS = all.stream().map(person -> new PersonDTO(person.getFirstName(),
                person.getLastName(), person.getDateOfBirth(),
                person.getGender(), formBaseRESTFulURLForUsersEmails(person.getIdperson()))).collect(toList());
        final String toJson = PRETTY_PRINTING_BUILDER.create().toJson(personDTOS);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(toJson);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/users/{idperson}")
    public ResponseEntity<String> getUserInfo(@PathVariable int idperson) {
        LOGGER.info("Start retrieve person with id {} from database", idperson);
        final Person personById = userDAO.findById(idperson);
        LOGGER.info("Person {} retrieved from database", personById);
        // TODO: 3/6/2018 get web path parameters from settings or context
        final PersonDTO personDTO = new PersonDTO(personById.getFirstName(), personById.getLastName(),
                personById.getDateOfBirth(), personById.getGender(), formBaseRESTFulURLForUsersEmails(personById.getIdperson()));
        final String toJson = PRETTY_PRINTING_BUILDER.create().toJson(personDTO);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(toJson);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{idperson}/emails")
    public ResponseEntity<String> getUserEmails(@PathVariable int idperson) {
        LOGGER.info("Start retrieve all emails for person with id {} from database", idperson);
        final Person byId = userDAO.findById(idperson);
        List<Emails> emailsList = byId.getEmailsList();
        LOGGER.info("Emails with size {} retrieved from database", emailsList.size());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(emailsList.toString());
    }
}

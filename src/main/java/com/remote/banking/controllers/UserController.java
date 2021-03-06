package com.remote.banking.controllers;

import com.remote.banking.models.dao.UserDAO;
import com.remote.banking.models.dto.EmailsDTO;
import com.remote.banking.models.dto.PersonDTO;
import com.remote.banking.models.for_rdbms.Emails;
import com.remote.banking.models.for_rdbms.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.remote.banking.app.SpringBootRestApplication.PRETTY_PRINTING_BUILDER;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("${api.root}")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserDAO userDAO;
    private final ServerProperties serverProperties;

    @Value("${api.root}")
    private String apiRoot;
    @Value("${api.root.users}")
    private String apiRootUsers;

    public UserController(UserDAO userDAO, ServerProperties serverProperties) {
        this.userDAO = userDAO;
        this.serverProperties = serverProperties;
    }

    private String formBaseRESTFulURLForUsers() {
        final String hostAddress = serverProperties.getAddress().getHostAddress();
        final int port = serverProperties.getPort();
        final String contextPath = serverProperties.getServlet().getContextPath();
        final String actualUrl = "http://" + hostAddress + ":" + port + contextPath + apiRoot + apiRootUsers;
        return actualUrl;
    }

    private String formBaseRESTFulURLForUser(final int idperson) {
        return formBaseRESTFulURLForUsers() + "/" + idperson;
    }

    private String formBaseRESTFulURLForUsersEmails(final int idperson) {
        return formBaseRESTFulURLForUser(idperson) + "/emails";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ResponseEntity<String> getUserInfo() {
        LOGGER.info("Start retrieve allPersons persons from database");
        final List<Person> allPersons = userDAO.findAllPersons();
        LOGGER.info("All persons from database have been retrieved");
        final List<PersonDTO> personDTOS = allPersons.stream()
                .map(person -> new PersonDTO(person.getFirstName(), person.getLastName(),
                        person.getDateOfBirth(), person.getGender(),
                        formBaseRESTFulURLForUsersEmails(person.getIdperson())))
                .collect(toList());
        final String toJson = PRETTY_PRINTING_BUILDER.create().toJson(personDTOS);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(toJson);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserWithNoEmail(@RequestBody final String jsonBody,
                                      final HttpServletRequest request, final HttpServletResponse response) {
        LOGGER.info("Receive: {}", jsonBody);
        final Person person = PRETTY_PRINTING_BUILDER.create().fromJson(jsonBody, Person.class);
        final int newUserId = userDAO.createAndStoreNewUser(person);
        response.setHeader("Location", request.getRequestURL()
                .append("/")
                .append(newUserId)
                .toString());
    }


    @RequestMapping(method = RequestMethod.GET, value = "/users/{idperson}")
    public ResponseEntity<String> getUserInfo(@PathVariable int idperson) {
        LOGGER.info("Start retrieve person with id {} from database", idperson);
        final Person personById = userDAO.findById(idperson);
        LOGGER.info("Person {} retrieved from database", personById);
        final PersonDTO personDTO = new PersonDTO(personById.getFirstName(), personById.getLastName(),
                personById.getDateOfBirth(), personById.getGender(),
                formBaseRESTFulURLForUsersEmails(personById.getIdperson()));
        final String toJson = PRETTY_PRINTING_BUILDER.create().toJson(personDTO);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(toJson);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{idperson}/emails")
    public ResponseEntity<String> getUserEmails(@PathVariable int idperson) {
        LOGGER.info("Start retrieve all emails for person with id {} from database", idperson);
        final Person byId = userDAO.findById(idperson);
        final List<Emails> emailsList = byId.getEmailsList();
        LOGGER.info("Emails with size {} retrieved from database", emailsList.size());
        final List<EmailsDTO> emailsDTOS = emailsList.stream()
                .map(email -> new EmailsDTO(email.getEmail(), formBaseRESTFulURLForUsers() + " TODO",
                        formBaseRESTFulURLForUser(email.getPersonIdperson().getIdperson())))
                .collect(toList());
        final String toJson = PRETTY_PRINTING_BUILDER.create().toJson(emailsDTOS);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(toJson);
    }
}

package com.remote.banking.controllers;

import com.remote.banking.models.dao.UserDAO;
import com.remote.banking.models.for_rdbms.Person;
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
@RequestMapping("${api.root}/hello")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ResponseEntity<String> getUserInfo() {

        final List<Person> all = userDAO.findAllPersons();

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(all.toString());
    }


    @RequestMapping(method = RequestMethod.GET, value = "/users/{idperson}")
    public ResponseEntity<String> getUserInfo(@PathVariable int idperson) {

        final Person byId = userDAO.findById(idperson);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(byId.toString());
    }
}

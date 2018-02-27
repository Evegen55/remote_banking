package com.remote.banking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.root}/hello")
public class SimpleRestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleRestController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/world")
    public ResponseEntity<String> hello() {
        LOGGER.info("Simple REST controller has been invoked");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body("Hello, world!");
    }
}

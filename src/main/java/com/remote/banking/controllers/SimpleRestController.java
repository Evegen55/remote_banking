package com.remote.banking.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.root}/hello")
public class SimpleRestController {

    @RequestMapping(method = RequestMethod.GET, value = "/world")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body("Hello, world!");
    }
}

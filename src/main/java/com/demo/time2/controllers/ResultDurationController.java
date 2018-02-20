package com.demo.time2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultDurationController {

    @RequestMapping(value = "/getString", method = RequestMethod.GET)
    public ResponseEntity<String> getString(@RequestParam("int") int id) {
        return new ResponseEntity<>("ssss" + id, HttpStatus.OK);
    }
}

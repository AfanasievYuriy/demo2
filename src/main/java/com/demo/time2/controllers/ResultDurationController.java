package com.demo.time2.controllers;

import com.demo.time2.domain.ResultDuration;
import com.demo.time2.service.ResultsHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultDurationController {

    @Autowired
    private ResultsHolder resultsHolder;

    @RequestMapping(value = "/getString", method = RequestMethod.GET)
    public ResponseEntity<String> getString(@RequestParam("int") int id) {
        ResultDuration duration = resultsHolder.getResultDurationByDate("20-01-2018");
        return new ResponseEntity<>("ssss" + duration.toString(), HttpStatus.OK);
    }
}

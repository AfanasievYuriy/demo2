package com.demo.time2.controllers;

import com.demo.time2.DTO.ResultDurationDTO;
import com.demo.time2.domain.ResultDuration;
import com.demo.time2.service.ResultsHolder;
import java.util.List;
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

    @RequestMapping(value = "/result", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResultDurationDTO> getResultByDate(@RequestParam("date") String date) {
        ResultDuration duration = resultsHolder.getResultDurationByDate(date);
        return new ResponseEntity<>(new ResultDurationDTO(duration, date), HttpStatus.OK);
    }

    @RequestMapping(value = "/result/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ResultDurationDTO>> getAllResults() {
        List<ResultDurationDTO> allResultDurations = resultsHolder.getAllResultDurations();
        return new ResponseEntity<>(allResultDurations, HttpStatus.OK);
    }
}

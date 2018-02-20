package com.demo.time2.controllers;

import com.demo.time2.service.ResultsHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @Autowired
    private ResultsHolder holder;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        System.out.println(holder);
        return "index";
    }
}

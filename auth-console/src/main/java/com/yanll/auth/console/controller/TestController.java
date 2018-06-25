package com.yanll.auth.console.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by YANLL on 2016/08/29.
 */
@RestController
@RequestMapping
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @RequestMapping(value = "/test")
    public String test() {
        return "SUCCESS!";
    }

}


package com.yanll.auth.console.controller;


import com.yanll.framework.auth.permission.annotation.ConsolePermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by YANLL on 2016/08/29.
 */
@ConsolePermission(controlled = false)
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @RequestMapping(value = "/index", method = RequestMethod.GET, name = "index")
    public String index() {
        return "index";
    }


}


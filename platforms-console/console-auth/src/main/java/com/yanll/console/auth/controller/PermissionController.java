package com.yanll.console.auth.controller;


import com.yanll.console.auth.manager.PermissionManager;
import com.yanll.framework.util.exception.BizCode;
import com.yanll.framework.web.result.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by YANLL on 2016/08/29.
 */
@RestController
@RequestMapping(value = "/permission")
public class PermissionController {
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    PermissionManager permissionManager;

}


package com.yanll.console.auth.controller;


import com.yanll.console.auth.manager.OperationManager;
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
@RequestMapping(value = "/console/operation")
public class OperationController {
    private static final Logger logger = LoggerFactory.getLogger(OperationController.class);
    @Autowired
    OperationManager operationManager;

    @RequestMapping(value = "/list", method = RequestMethod.GET, name = "查询操作列表")
    @ResponseBody
    public JSON list() {
        return new JSON(BizCode.OK.getValue(), operationManager.getOperations());
    }

}


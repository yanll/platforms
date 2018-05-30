package com.yanll.auth.console.controller;


import com.yanll.auth.console.manager.PermissionManager;
import com.yanll.auth.service.domain.PermissionGroupBeanDTO;
import com.yanll.framework.facade.domain.AjaxResult;
import com.yanll.framework.facade.exception.BizCode;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;
import com.yanll.framework.facade.page.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


package com.yanll.console.auth.controller;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.domain.PermissionGroupBeanVO;
import com.yanll.console.auth.manager.PermissionGroupManager;
import com.yanll.framework.util.exception.BizCode;
import com.yanll.framework.util.page.PaginateWrapper;
import com.yanll.framework.util.page.PaginationUtil;
import com.yanll.framework.web.result.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by YANLL on 2016/08/29.
 */
@RestController
@RequestMapping(value = "/permission_group")
public class PermissionGroupController {
    private static final Logger logger = LoggerFactory.getLogger(PermissionGroupController.class);
    @Autowired
    PermissionGroupManager permissionGroupManager;


    @RequestMapping(value = "/list", method = RequestMethod.GET, name = "查询权限组列表")
    @ResponseBody
    public JSON<PaginateWrapper<List<PermissionGroupBeanVO>>> list(Integer page, Integer limit) {
        PageBounds pageBounds = PaginationUtil.toPageBounds(page, limit, true);
        return new JSON(BizCode.OK.getValue(), permissionGroupManager.getPermissionGroups(pageBounds));
    }

}


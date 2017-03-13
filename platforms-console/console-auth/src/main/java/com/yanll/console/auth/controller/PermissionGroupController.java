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
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/list/{portal_id}", method = RequestMethod.GET, name = "查询权限分组列表")
    @ResponseBody
    public JSON<PaginateWrapper<List<PermissionGroupBeanVO>>> list(@PathVariable Long portal_id, Integer page, Integer limit) {
        PageBounds pageBounds = PaginationUtil.toPageBounds(page, limit, true);
        return new JSON(BizCode.OK.getValue(), permissionGroupManager.getPermissionGroups(portal_id, pageBounds));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, name = "权限分组详情")
    @ResponseBody
    public JSON<PermissionGroupBeanVO> get(@PathVariable Long id) {
        return new JSON(permissionGroupManager.detail(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, name = "删除权限分组")
    @ResponseBody
    public JSON delete(@PathVariable Long id) {
        permissionGroupManager.delete(id);
        return new JSON(BizCode.OK.getValue());
    }

    @RequestMapping(method = RequestMethod.POST, name = "保存权限分组")
    @ResponseBody
    public JSON save(@RequestBody PermissionGroupBeanVO permissionGroupBeanVO) {
        permissionGroupManager.save(permissionGroupBeanVO);
        return new JSON(BizCode.OK.getValue());
    }

    @RequestMapping(method = RequestMethod.PUT, name = "更新权限分组")
    @ResponseBody
    public JSON update(@RequestBody PermissionGroupBeanVO permissionGroupBeanVO) {
        permissionGroupManager.update(permissionGroupBeanVO);
        return new JSON(BizCode.OK.getValue());
    }

    @RequestMapping(value = "/{id}/permissions", method = RequestMethod.GET, name = "查询权限分组权限列表")
    @ResponseBody
    public JSON<PaginateWrapper<List<PermissionGroupBeanVO>>> permissions(@PathVariable Long id, Integer page, Integer limit) {
        PageBounds pageBounds = PaginationUtil.toPageBounds(page, limit, true);
        return new JSON(BizCode.OK.getValue(), permissionGroupManager.getPermissions(id, pageBounds));
    }

}


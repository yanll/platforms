package com.yanll.auth.console.controller;


import com.yanll.auth.console.manager.PermissionGroupManager;
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
@RequestMapping(value = "/permission_group")
public class PermissionGroupController {
    private static final Logger logger = LoggerFactory.getLogger(PermissionGroupController.class);
    @Autowired
    PermissionGroupManager permissionGroupManager;

    @RequestMapping(value = "/list/{portal_id}", method = RequestMethod.GET, name = "查询权限分组列表")
    @ResponseBody
    public AjaxResult<PaginateWrapper<List<PermissionGroupBeanDTO>>> list(@PathVariable Long portal_id, Integer page, Integer limit) {
        Pagination pagination = PaginationUtil.toPageBounds(page, limit);
        return new AjaxResult(BizCode.OK.getValue(), permissionGroupManager.getPermissionGroups(portal_id, pagination));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, name = "删除权限分组")
    @ResponseBody
    public AjaxResult delete(@PathVariable Long id) {
        permissionGroupManager.delete(id);
        return new AjaxResult(BizCode.OK.getValue());
    }

    @RequestMapping(method = RequestMethod.POST, name = "保存权限分组")
    @ResponseBody
    public AjaxResult save(@RequestBody PermissionGroupBeanDTO permissionGroupBeanDTO) {
        permissionGroupManager.save(permissionGroupBeanDTO);
        return new AjaxResult(BizCode.OK.getValue());
    }

    @RequestMapping(method = RequestMethod.PUT, name = "更新权限分组")
    @ResponseBody
    public AjaxResult update(@RequestBody PermissionGroupBeanDTO permissionGroupBeanVO) {
        permissionGroupManager.save(permissionGroupBeanVO);
        return new AjaxResult(BizCode.OK.getValue());
    }

//    @RequestMapping(value = "/permissions/{group_id}", method = RequestMethod.GET, name = "查询权限分组权限列表")
//    @ResponseBody
//    public AjaxResult<PaginateWrapper<List<PermissionGroupBeanDTO>>> permissions(@PathVariable Long group_id, Integer page, Integer limit) {
//        Pagination pagination = PaginationUtil.toPageBounds(page, limit);
//        return new AjaxResult(BizCode.OK.getValue(), permissionGroupManager.getPermissions(group_id, 1L,pagination));
//    }


}


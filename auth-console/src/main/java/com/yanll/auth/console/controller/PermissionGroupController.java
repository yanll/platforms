package com.yanll.auth.console.controller;


import com.yanll.auth.console.manager.RoleManager;
import com.yanll.auth.service.domain.PermissionGroupBeanDTO;
import com.yanll.auth.service.domain.RoleBeanDTO;
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
    RoleManager permissionGroupManager;

    @RequestMapping(value = "/list/{system_code}", method = RequestMethod.GET, name = "查询权限分组列表")
    @ResponseBody
    public AjaxResult<PaginateWrapper<List<RoleBeanDTO>>> list(@PathVariable String system_code, Integer page, Integer limit) {
        Pagination pagination = PaginationUtil.toPageBounds(page, limit);
        return new AjaxResult(BizCode.OK.getValue(), permissionGroupManager.getPermissionGroups(system_code, pagination));
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

    @RequestMapping(value = "/permissions/{system_code}/{role_id}", method = RequestMethod.GET, name = "查询权限分组树形权限列表")
    @ResponseBody
    public AjaxResult<List<RoleBeanDTO>> permissions(@PathVariable String system_code, @PathVariable Long role_id) {
        return new AjaxResult(BizCode.OK.getValue(), permissionGroupManager.getPermissions(system_code, role_id));
    }


}


package com.yanll.auth.console.controller;


import com.yanll.auth.console.manager.RoleManager;
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
@RequestMapping(value = "/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    RoleManager roleManager;

    @RequestMapping(value = "/list/{system_code}", method = RequestMethod.GET, name = "查询角色列表")
    @ResponseBody
    public AjaxResult<PaginateWrapper<List<RoleBeanDTO>>> list(@PathVariable String system_code, Integer page, Integer limit) {
        Pagination pagination = PaginationUtil.toPageBounds(page, limit);
        return new AjaxResult(BizCode.OK.getValue(), roleManager.getRoles(system_code, pagination));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, name = "删除角色")
    @ResponseBody
    public AjaxResult delete(@PathVariable Long id) {
        roleManager.delete(id);
        return new AjaxResult(BizCode.OK.getValue());
    }

    @RequestMapping(method = RequestMethod.POST, name = "保存角色")
    @ResponseBody
    public AjaxResult save(@RequestBody RoleBeanDTO roleBeanDTO) {
        roleManager.save(roleBeanDTO);
        return new AjaxResult(BizCode.OK.getValue());
    }

    @RequestMapping(method = RequestMethod.PUT, name = "更新角色")
    @ResponseBody
    public AjaxResult update(@RequestBody RoleBeanDTO roleBeanDTO) {
        roleManager.save(roleBeanDTO);
        return new AjaxResult(BizCode.OK.getValue());
    }

    @RequestMapping(value = "/permissions/{system_code}/{role_id}", method = RequestMethod.GET, name = "查询角色树形权限列表")
    @ResponseBody
    public AjaxResult<List<RoleBeanDTO>> permissions(@PathVariable String system_code, @PathVariable Long role_id) {
        return new AjaxResult(BizCode.OK.getValue(), roleManager.getPermissions(system_code, role_id));
    }


}


package com.yanll.auth.console.controller;


import com.yanll.auth.console.manager.UserManager;
import com.yanll.auth.service.domain.UserBeanDTO;
import com.yanll.framework.facade.domain.AjaxResult;
import com.yanll.framework.facade.exception.BizCode;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;
import com.yanll.framework.facade.page.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Created by YANLL on 2016/08/29.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserManager userManager;

    @RequestMapping(value = "/list", method = RequestMethod.GET, name = "查询用户列表")
    @ResponseBody
    public AjaxResult<PaginateWrapper<List<UserBeanDTO>>> list(Integer page, Integer limit) {
        Pagination pagination = PaginationUtil.toPageBounds(page, limit);
        return new AjaxResult(BizCode.OK.getValue(), userManager.getUsers(pagination));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, name = "权限分组详情")
    @ResponseBody
    public AjaxResult<UserBeanDTO> get(@PathVariable Long id) {
        return new AjaxResult(userManager.getUser(id));
    }

    @RequestMapping(value = "/imp", method = RequestMethod.POST, name = "导入用户")
    @ResponseBody
    public AjaxResult imp(@RequestParam("file") MultipartFile file) {
        userManager.imp(file);
        return new AjaxResult(BizCode.OK.getValue());
    }

    @RequestMapping(method = RequestMethod.POST, name = "保存用户")
    @ResponseBody
    public AjaxResult save(@RequestBody UserBeanDTO user) {
        userManager.save(user);
        return new AjaxResult(BizCode.OK.getValue());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, name = "删除用户")
    @ResponseBody
    public AjaxResult del(@PathVariable Long id) {
        userManager.del(id);
        return new AjaxResult(BizCode.OK.getValue());
    }

    @RequestMapping(method = RequestMethod.PUT, name = "更新用户")
    @ResponseBody
    public AjaxResult update(@RequestBody UserBeanDTO userVO) {
        userManager.update(userVO);
        return new AjaxResult(BizCode.OK.getValue());
    }

    @RequestMapping(value = "/resetpwd/{id}", method = RequestMethod.PUT, name = "更新用户")
    @ResponseBody
    public AjaxResult resetpwd(@PathVariable Long id) {
        userManager.updatePwd(id);
        return new AjaxResult(BizCode.OK.getValue());
    }

}


package com.yanll.console.auth.controller;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.domain.UserBeanVO;
import com.yanll.console.auth.manager.UserManager;
import com.yanll.framework.util.exception.BizCode;
import com.yanll.framework.util.page.PaginateWrapper;
import com.yanll.framework.util.page.PaginationUtil;
import com.yanll.framework.web.result.JSON;
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
    public JSON<PaginateWrapper<List<UserBeanVO>>> list(Integer page, Integer limit) {
        PageBounds pageBounds = PaginationUtil.toPageBounds(page, limit, true);
        return new JSON(BizCode.OK.getValue(), userManager.getUsers(pageBounds));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, name = "权限分组详情")
    @ResponseBody
    public JSON<UserBeanVO> get(@PathVariable Long id) {
        return new JSON(userManager.getUser(id));
    }

    @RequestMapping(value = "/imp", method = RequestMethod.POST, name = "导入用户")
    @ResponseBody
    public JSON imp(@RequestParam("file") MultipartFile file) {
        userManager.imp(file);
        return new JSON(BizCode.OK.getValue());
    }

    @RequestMapping(method = RequestMethod.POST, name = "保存用户")
    @ResponseBody
    public JSON save(@RequestBody UserBeanVO user) {
        userManager.save(user);
        return new JSON(BizCode.OK.getValue());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, name = "删除用户")
    @ResponseBody
    public JSON del(@PathVariable Long id) {
        userManager.del(id);
        return new JSON(BizCode.OK.getValue());
    }

    @RequestMapping(method = RequestMethod.PUT, name = "更新用户")
    @ResponseBody
    public JSON update(@RequestBody UserBeanVO userVO) {
        userManager.update(userVO);
        return new JSON(BizCode.OK.getValue());
    }

    @RequestMapping(value = "/resetpwd/{id}", method = RequestMethod.PUT, name = "更新用户")
    @ResponseBody
    public JSON resetpwd(@PathVariable Long id) {
        userManager.updatePwd(id);
        return new JSON(BizCode.OK.getValue());
    }

}


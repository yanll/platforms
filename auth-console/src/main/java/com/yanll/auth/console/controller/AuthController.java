package com.yanll.auth.console.controller;


import com.yanll.auth.console.manager.AuthManager;
import com.yanll.auth.service.domain.UserBeanDTO;
import com.yanll.framework.auth.permission.annotation.ConsolePermission;
import com.yanll.framework.facade.domain.AjaxResult;
import com.yanll.framework.facade.exception.BizException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
@ConsolePermission(controlled = false)
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    AuthManager authManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST, name = "用户登录")
    @ResponseBody
    public AjaxResult<UserBeanDTO> login(HttpServletRequest request, String username, String password) {
        if (username == null) throw new BizException("用户名不能为空!");
        if (password == null) throw new BizException("密码不能为空!");
        UserBeanDTO userBeanDTO = authManager.login(username, password);
        return new AjaxResult<UserBeanDTO>(200, userBeanDTO);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, name = "注销登录")
    @ResponseBody
    public AjaxResult logout(HttpServletRequest request) {
        return null;
    }

}


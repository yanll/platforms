package com.yanll.auth.console.controller;


import com.yanll.auth.service.service.IAuthService;
import com.yanll.auth.service.service.IUserService;
import com.yanll.framework.auth.permission.annotation.ConsolePermission;
import com.yanll.framework.facade.domain.AjaxResult;
import com.yanll.framework.facade.exception.BizCode;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.techforge.patron.SecurityUtils;
import tk.techforge.patron.Subject;
import tk.techforge.patron.authentication.AuthenticationToken;
import tk.techforge.patron.authentication.UsernamePasswordToken;


/**
 * Created by YANLL on 2016/08/29.
 */
@ConsolePermission(controlled = false)
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    IAuthService authService;
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, name = "用户登录")
    @ResponseBody
    public AjaxResult login(HttpServletRequest request, String username, String password) {
        /*
        UserBeanDTO vo = userService.selectUser(username, password);
        if (vo != null && vo.getId() != null) {
            Map<String, String> map = new HashMap();
            map.put("/console/menu/list:GET", "");
            request.getSession().setAttribute("user", username);
            request.getSession().setAttribute("user_permission", map);
        }
        return new AjaxResult(BizCode.OK.getValue(), vo);
        */

        AuthenticationToken token = new UsernamePasswordToken(username, password.toCharArray());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return new AjaxResult(200, subject);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, name = "注销登录")
    @ResponseBody
    public AjaxResult logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("user_permission");
        return new AjaxResult(BizCode.OK.getValue());
    }

}


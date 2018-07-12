package com.yanll.auth.console.controller;


import com.yanll.auth.console.manager.AuthManager;
import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.auth.service.domain.UserBeanDTO;
import com.yanll.framework.auth.permission.annotation.ConsolePermission;
import com.yanll.framework.facade.domain.AjaxResult;
import com.yanll.framework.facade.exception.BizException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import tk.techforge.patron.Permissions;
import tk.techforge.patron.exceptions.AuthException;
import tk.techforge.patron.subject.Subject;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


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

    @Permissions(required = false)
    @RequestMapping(value = "/login", method = RequestMethod.POST, name = "用户登录")
    public AjaxResult<UserBeanDTO> login(@RequestBody UserBeanDTO user, HttpServletRequest request) {
        if (user == null) throw new BizException("认证参数不能为空!");
        if (user.getUsername() == null) throw new BizException("用户名不能为空!");
        if (user.getPassword() == null) throw new BizException("密码不能为空!");
        UserBeanDTO userBeanDTO = null;
        try {
            Subject<UserBeanDTO, PermissionBeanDTO> subject = authManager.login(user.getUsername(), user.getPassword());
            userBeanDTO = subject.getPrincipal();
        } catch (AuthException e) {
            logger.error(user.getUsername() + ":" + e.getMessage(), e);
            return new AjaxResult<UserBeanDTO>(HttpStatus.FORBIDDEN.value(), "认证失败！");
        }
        return new AjaxResult<UserBeanDTO>(HttpStatus.OK.value(), userBeanDTO);
    }

    @Permissions(required = false)
    @RequestMapping(value = "/logout", method = RequestMethod.GET, name = "注销登录")
    public AjaxResult logout(HttpServletRequest request) {
        authManager.logout();
        return new AjaxResult<UserBeanDTO>(HttpStatus.FORBIDDEN.value());
    }


    @Permissions(required = false)
    @RequestMapping(value = "/init", method = RequestMethod.GET, name = "初始化映射")
    public AjaxResult init(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) {
        List<String> list = new ArrayList<String>();
        WebApplicationContext wac = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        RequestMappingHandlerMapping bean = wac.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
            PatternsRequestCondition prc = rmi.getPatternsCondition();
            RequestMethodsRequestCondition rrc = rmi.getMethodsCondition();
            Set<String> patterns = prc.getPatterns();
            Set<RequestMethod> methods = rrc.getMethods();
            HandlerMethod method = handlerMethods.get(rmi);
            Permissions permissions = method.getMethodAnnotation(Permissions.class);
            boolean controlled = true;
            if (permissions != null && !permissions.required()) {
                controlled = false;
            }
            for (String p : patterns) {
                for (RequestMethod m : methods) {
                    String uri = p + ":" + m.name();
                    String sql = "insert into m_permission (system_code,menu_id,url,permission_name) select 'auth_console',id,'" + uri + "','" + rmi.getName() + "' from m_menu where parent_id is not null;";
                    if (controlled) {
                        logger.info(rmi.getName() + "：" + uri);
                        logger.info(sql);
                        list.add(sql);
                    }
                }
            }
        }
        return new AjaxResult(HttpStatus.OK.value(), list);
    }

}


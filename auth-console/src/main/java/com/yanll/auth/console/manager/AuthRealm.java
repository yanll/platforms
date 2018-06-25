package com.yanll.auth.console.manager;

import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.auth.service.domain.UserBeanDTO;
import com.yanll.auth.service.service.IUserService;
import com.yanll.framework.facade.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.techforge.patron.AbstractSecurityManager;
import tk.techforge.patron.auth.AuthInfo;
import tk.techforge.patron.auth.AuthToken;
import tk.techforge.patron.auth.SimpleAuthInfo;
import tk.techforge.patron.auth.UsernamePasswordToken;
import tk.techforge.patron.exceptions.AuthException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthRealm extends AbstractSecurityManager {
    private static final Logger logger = LoggerFactory.getLogger(AuthRealm.class);

    @Autowired
    IUserService userService;

    @Override
    protected AuthInfo doGetAuthentication(AuthToken token) throws AuthException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        UserBeanDTO userBeanDTO = null;
        try {
            userBeanDTO = userService.selectUser(usernamePasswordToken.getUsername(), new String(usernamePasswordToken.getPassword()));
        } catch (BizException e) {
            throw new AuthException(e.getMsg());
        }
        if (userBeanDTO == null || userBeanDTO.getId() == null) throw new AuthException("账户认证失败！");
        usernamePasswordToken.clear();
        SimpleAuthInfo simpleAuthenticationInfo = new SimpleAuthInfo();
        simpleAuthenticationInfo.setPrincipal(userBeanDTO);
        return simpleAuthenticationInfo;
    }

    @Override
    protected Collection<?> doGetAuthorization(Object principal) {
        PermissionBeanDTO p = new PermissionBeanDTO();
        p.setUrl("/test");
        p.setPermissionName("添加用户");
        List<PermissionBeanDTO> list = new ArrayList<>();
        list.add(p);
        return list;
    }
}

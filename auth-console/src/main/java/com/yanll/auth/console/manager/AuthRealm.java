package com.yanll.auth.console.manager;

import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.auth.service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.techforge.patron.AbstractSecurityManager;
import tk.techforge.patron.authentication.AuthenticationInfo;
import tk.techforge.patron.authentication.AuthenticationToken;
import tk.techforge.patron.authentication.SimpleAuthenticationInfo;
import tk.techforge.patron.authorization.AuthorizationInfo;
import tk.techforge.patron.authorization.SimpleAuthorizationInfo;
import tk.techforge.patron.exceptions.AuthenticationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthRealm extends AbstractSecurityManager {

    @Autowired
    IUserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo();
        simpleAuthenticationInfo.setPrincipals("admin");
        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(Collection principal) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        PermissionBeanDTO p = new PermissionBeanDTO();
        p.setUrl("/test");
        p.setPermissionName("添加用户");
        List<PermissionBeanDTO> list = new ArrayList<>();
        list.add(p);
        simpleAuthorizationInfo.setPermissions(list);
        return simpleAuthorizationInfo;
    }
}

package com.yanll.auth.console.manager;

import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.auth.service.domain.UserBeanDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.techforge.patron.AuthHandler;
import tk.techforge.patron.auth.AuthToken;
import tk.techforge.patron.auth.UsernamePasswordToken;
import tk.techforge.patron.exceptions.AuthException;
import tk.techforge.patron.subject.Subject;
import tk.techforge.patron.utils.SecurityUtils;

/**
 * Created by YANLL on 2016/12/7.
 */
@Component
public class AuthManager {
    private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);

    @Autowired
    AuthHandler<UserBeanDTO, PermissionBeanDTO> authHandler;

    public Subject<UserBeanDTO, PermissionBeanDTO> login(String username, String password) throws AuthException {
        AuthToken token = new UsernamePasswordToken(username, password.toCharArray());
        Subject<UserBeanDTO, PermissionBeanDTO> subject = authHandler.login(token);
        if (subject == null || subject.getId() == null) throw new AuthException("认证失败！");
        return subject;
    }

    public void logout() {
        Subject<UserBeanDTO, PermissionBeanDTO> subject = SecurityUtils.getSubject();
        authHandler.logout(subject);
    }
}

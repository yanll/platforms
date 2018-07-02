package com.yanll.auth.console.manager;

import com.yanll.auth.service.domain.UserBeanDTO;
import com.yanll.auth.service.service.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.techforge.patron.utils.SecurityUtils;
import tk.techforge.patron.subject.Subject;
import tk.techforge.patron.auth.AuthToken;
import tk.techforge.patron.auth.UsernamePasswordToken;

/**
 * Created by YANLL on 2016/12/7.
 */
@Component
public class AuthManager {
    private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);
    @Autowired
    IAuthService authService;


    public UserBeanDTO login(String username, String password) {
        AuthToken token = new UsernamePasswordToken(username, password.toCharArray());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        UserBeanDTO userBeanDTO = null;//(UserBeanDTO) subject.getAuthInfo().getPrincipal();
        return userBeanDTO;
    }
}

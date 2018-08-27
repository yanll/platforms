package com.yanll.auth.console.manager;

import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.auth.service.domain.UserBeanDTO;
import com.yanll.auth.service.service.IPermissionService;
import com.yanll.auth.service.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.techforge.mock.localcahce.LocalCache;
import tk.techforge.patron.AuthHandler;
import tk.techforge.patron.auth.AuthToken;
import tk.techforge.patron.exceptions.AuthException;
import tk.techforge.patron.subject.Subject;

import java.util.Collection;
import java.util.List;

@Component
public class DefaultAuthHandler implements AuthHandler<UserBeanDTO, PermissionBeanDTO> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultAuthHandler.class);

    @Autowired
    IUserService userService;
    @Autowired
    IPermissionService permissionService;

    @Override
    public Subject<UserBeanDTO, PermissionBeanDTO> login(AuthToken authToken) throws AuthException {
        Subject<UserBeanDTO, PermissionBeanDTO> subject = new Subject<>();
        try {
            UserBeanDTO userBeanDTO = userService.selectUser((String) authToken.getPrincipal(), new String((char[]) authToken.getCredentials()));
            if (userBeanDTO != null || userBeanDTO.getId() != null) {
                subject.setId(userBeanDTO.getId());
                subject.setPrincipal(userBeanDTO);
                subject.setAuthenticated(true);
                LocalCache.put(subject.getId().toString(), subject, 1000 * 60 * 5);
            }
            logger.error("authentication not exists:" + authToken.toString());
        } catch (Exception e) {
            throw new AuthException("authenticate error:" + authToken.toString() + "," + e.getMessage());
        } finally {
            authToken.clear();
        }
        return subject;
    }

    @Override
    public void logout(Subject<UserBeanDTO, PermissionBeanDTO> subject) throws AuthException {
        if (subject == null) return;
        LocalCache.remove(subject.getId().toString());
    }

    @Override
    public Collection<PermissionBeanDTO> getPermisssons(Subject<UserBeanDTO, PermissionBeanDTO> subject) {
        if (subject == null) return null;
        if (subject.getAuthenticated() == null || !subject.getAuthenticated()) return null;
        UserBeanDTO userBeanDTO = subject.getPrincipal();
        List<PermissionBeanDTO> permissions = permissionService.selectPermissionsByUserId(userBeanDTO.getId());
        return permissions;
    }

    @Override
    public Subject<UserBeanDTO, PermissionBeanDTO> getAuthenticatedSubject() {
        Object o = LocalCache.get("10001");
        if (o != null) return (Subject<UserBeanDTO, PermissionBeanDTO>) o;
        return null;
    }

    @Override
    public boolean hasPermission(Collection<PermissionBeanDTO> permissions, String permission) {
        if (permissions == null || permissions.size() == 0) return false;
        for (PermissionBeanDTO p : permissions) {
            if (p.getUrl().equals(permission)) return true;
        }
        return false;
    }
}

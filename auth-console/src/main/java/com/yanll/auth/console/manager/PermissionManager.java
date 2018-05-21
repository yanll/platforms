package com.yanll.auth.console.manager;

import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.auth.service.service.IPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by YANLL on 2016/12/7.
 */
@Component
public class PermissionManager {
    private static final Logger logger = LoggerFactory.getLogger(PermissionManager.class);
    @Autowired
    IPermissionService permissionService;


    public List<PermissionBeanDTO> getPermissions(Long portal_id) {
        return permissionService.selectPermissions(portal_id);
    }
}

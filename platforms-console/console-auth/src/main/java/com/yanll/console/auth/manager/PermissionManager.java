package com.yanll.console.auth.manager;

import com.yanll.business.auth.domain.PermissionBeanVO;
import com.yanll.business.auth.service.IPermissionService;
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


    public List<PermissionBeanVO> getPermissions() {
        return permissionService.selectPermissions();
    }
}

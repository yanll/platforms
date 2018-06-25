package com.yanll.auth.console.manager;

import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.auth.service.service.IPermissionService;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;
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


    public PaginateWrapper<List<PermissionBeanDTO>> getPermissions(String system_code, Pagination pagination) {
        return permissionService.selectPermissions(system_code, pagination);
    }
}

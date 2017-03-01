package com.yanll.console.auth.manager;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.domain.PermissionGroupBeanVO;
import com.yanll.business.auth.service.IPermissionGroupService;
import com.yanll.framework.util.page.PaginateWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by YANLL on 2016/12/7.
 */
@Component
public class PermissionGroupManager {
    private static final Logger logger = LoggerFactory.getLogger(PermissionGroupManager.class);
    @Autowired
    IPermissionGroupService permissionGroupService;


    public PaginateWrapper<List<PermissionGroupBeanVO>> getPermissionGroups(PageBounds pageBounds) {
        return permissionGroupService.selectPermissionGroups(pageBounds);
    }
}

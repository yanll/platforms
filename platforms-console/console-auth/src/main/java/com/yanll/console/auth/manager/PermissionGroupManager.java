package com.yanll.console.auth.manager;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.domain.PermissionBeanVO;
import com.yanll.business.auth.domain.PermissionGroupBeanVO;
import com.yanll.business.auth.service.IPermissionGroupService;
import com.yanll.business.auth.service.IPermissionService;
import com.yanll.framework.util.enums.EnumUtil;
import com.yanll.framework.util.enums.IEnum;
import com.yanll.framework.util.exception.BizException;
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
    @Autowired
    IPermissionService permissionService;


    public PaginateWrapper<List<PermissionGroupBeanVO>> getPermissionGroups(Long portal_id, PageBounds pageBounds) {
        return permissionGroupService.selectPermissionGroups(portal_id, pageBounds);
    }

    public PaginateWrapper<List<PermissionBeanVO>> getPermissions(Long group_id, PageBounds pageBounds) {
        return permissionService.selectPermissions(group_id, pageBounds);
    }

    public PermissionGroupBeanVO detail(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        return permissionGroupService.selectByPrimaryKey(id);
    }

    public void delete(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        permissionGroupService.deleteByPrimaryKey(id);
    }

    public void save(PermissionGroupBeanVO permissionGroupBeanVO) {
        if (permissionGroupBeanVO == null) throw new BizException("权限组对象不能为空！");
        Integer portal_id = permissionGroupBeanVO.getPortalId();
        if (!EnumUtil.exists(portal_id, IEnum.SYSTEM_PORTAL.class)) throw new BizException("Portal系统未知！");
        permissionGroupService.insertSelective(permissionGroupBeanVO);
    }

    public void update(PermissionGroupBeanVO permissionGroupBeanVO) {
        if (permissionGroupBeanVO == null) throw new BizException("权限组对象不能为空！");
        if (permissionGroupBeanVO.getId() == null) throw new BizException("主键不能为空！");
        permissionGroupService.updateByPrimaryKeySelective(permissionGroupBeanVO);
    }
}

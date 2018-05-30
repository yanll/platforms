package com.yanll.auth.console.manager;

import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.auth.service.domain.PermissionGroupBeanDTO;
import com.yanll.auth.service.service.IPermissionGroupService;
import com.yanll.auth.service.service.IPermissionService;
import com.yanll.framework.facade.exception.BizException;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;
import com.yanll.framework.util.enums.EnumUtil;
import com.yanll.framework.util.enums.IEnum;
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


    public PaginateWrapper<List<PermissionGroupBeanDTO>> getPermissionGroups(Long portal_id, Pagination pagination) {
        return permissionGroupService.selectPermissionGroups(portal_id, pagination);
    }

    public PaginateWrapper<List<PermissionBeanDTO>> getPermissions(Long group_id, Pagination pagination) {
        return permissionService.selectPermissions(group_id, pagination);
    }

    public PermissionGroupBeanDTO detail(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        return null;//permissionGroupService.selectByPrimaryKey(id);
    }

    public void delete(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        //permissionGroupService.deleteByPrimaryKey(id);
    }

    public void save(PermissionGroupBeanDTO permissionGroupBeanVO) {
        if (permissionGroupBeanVO == null) throw new BizException("权限组对象不能为空！");
        if (permissionGroupBeanVO.getGroupName() == null) throw new BizException("权限组名称不能为空！");
        Integer portal_id = permissionGroupBeanVO.getPortalId();
        if (!EnumUtil.exists(portal_id, IEnum.SYSTEM_PORTAL.class)) throw new BizException("Portal系统未知！");
        Integer count = permissionGroupService.selectCountByNameAndPortal(permissionGroupBeanVO.getPortalId().longValue(), permissionGroupBeanVO.getGroupName());
        if (count > 0) {
            throw new BizException("权限组名称已存在！");
        }
        //permissionGroupService.insertSelective(permissionGroupBeanVO);
    }

    public void update(PermissionGroupBeanDTO permissionGroupBeanVO) {
        if (permissionGroupBeanVO == null) throw new BizException("权限组对象不能为空！");
        if (permissionGroupBeanVO.getGroupName() == null) throw new BizException("权限组名称不能为空！");
        if (permissionGroupBeanVO.getId() == null) throw new BizException("主键不能为空！");
        //permissionGroupService.updateByPrimaryKeySelective(permissionGroupBeanVO);
    }
}


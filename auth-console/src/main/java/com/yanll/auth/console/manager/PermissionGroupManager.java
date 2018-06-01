package com.yanll.auth.console.manager;

import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.auth.service.domain.PermissionGroupBeanDTO;
import com.yanll.auth.service.service.IMenuService;
import com.yanll.auth.service.service.IPermissionGroupService;
import com.yanll.auth.service.service.IPermissionService;
import com.yanll.framework.facade.exception.BizException;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;
import com.yanll.framework.util.TreeUtil;
import com.yanll.framework.util.enums.EnumUtil;
import com.yanll.framework.util.enums.IEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    IMenuService menuService;


    public PaginateWrapper<List<PermissionGroupBeanDTO>> getPermissionGroups(Long portal_id, Pagination pagination) {
        if (portal_id == null) throw new BizException("Portal主键不能为空！");
        return permissionGroupService.selectPermissionGroups(portal_id, pagination);
    }

    public List<Map<String, Object>> getPermissions(Long portal_id, Long group_id) {
        List<Map<String, Object>> menus = menuService.selectAllMenus(portal_id);
        if (menus == null || menus.size() == 0) return new ArrayList<>();
        List<PermissionBeanDTO> permissions = permissionService.selectPermissions(group_id);
        if (permissions == null || permissions.size() == 0) return new ArrayList<>();
        for (PermissionBeanDTO v : permissions) {
            Map<String, Object> rec = new HashMap<>();
            rec.put("id", v.getId());
            rec.put("parent_id", v.getMenuId());
            rec.put("permission_name", v.getPermissionName());
            rec.put("menu_name", v.getPermissionName());
            menus.add(rec);
        }
        List<Map<String, Object>> tree = TreeUtil.buildMapTree(menus, "id", "parent_id");
        return tree;
    }

    public void delete(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        permissionGroupService.deleteByPrimaryKey(id);
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
        if (permissionGroupBeanVO.getId() == null) {
            permissionGroupService.insertSelective(permissionGroupBeanVO);
            return;
        }
        permissionGroupService.updateByPrimaryKeySelective(permissionGroupBeanVO);
    }
}


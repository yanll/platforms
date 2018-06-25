package com.yanll.auth.console.manager;

import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.auth.service.domain.RoleBeanDTO;
import com.yanll.auth.service.service.IMenuService;
import com.yanll.auth.service.service.IRoleService;
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
public class RoleManager {
    private static final Logger logger = LoggerFactory.getLogger(RoleManager.class);
    @Autowired
    IRoleService roleService;
    @Autowired
    IPermissionService permissionService;
    @Autowired
    IMenuService menuService;


    public PaginateWrapper<List<RoleBeanDTO>> getRoles(String system_code, Pagination pagination) {
        if (system_code == null) throw new BizException("系统主键不能为空！");
        return roleService.selectRoles(system_code, pagination);
    }

    public List<Map<String, Object>> getPermissions(String system_code, Long role_id) {
        List<Map<String, Object>> menus = menuService.selectAllMenus(system_code);
        if (menus == null || menus.size() == 0) return new ArrayList<>();
        List<PermissionBeanDTO> permissions = permissionService.selectPermissions(role_id);
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
        roleService.deleteByPrimaryKey(id);
    }

    public void save(RoleBeanDTO roleBeanDTO) {
        if (roleBeanDTO == null) throw new BizException("角色对象不能为空！");
        if (roleBeanDTO.getSystemCode() == null) throw new BizException("角色名称不能为空！");
        String system_code = roleBeanDTO.getSystemCode();
        if (!EnumUtil.exists(system_code, IEnum.SYSTEM_PORTAL.class)) throw new BizException("Portal系统未知！");
        Integer count = roleService.selectCountByNameAndSystem(roleBeanDTO.getSystemCode(), roleBeanDTO.getRoleName());
        if (count > 0) {
            throw new BizException("角色名称已存在！");
        }
        if (roleBeanDTO.getId() == null) {
            roleService.insertSelective(roleBeanDTO);
            return;
        }
        roleService.updateByPrimaryKeySelective(roleBeanDTO);
    }
}


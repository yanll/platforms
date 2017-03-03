package com.yanll.console.auth.manager;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.domain.PermissionGroupBeanVO;
import com.yanll.business.auth.service.IPermissionGroupService;
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


    public PaginateWrapper<List<PermissionGroupBeanVO>> getPermissionGroups(PageBounds pageBounds) {
        return permissionGroupService.selectPermissionGroups(pageBounds);
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
        permissionGroupService.insertSelective(permissionGroupBeanVO);
    }

    public void update(PermissionGroupBeanVO permissionGroupBeanVO) {
        if (permissionGroupBeanVO == null) throw new BizException("权限组对象不能为空！");
        if (permissionGroupBeanVO.getId() == null) throw new BizException("主键不能为空！");
        permissionGroupService.updateByPrimaryKeySelective(permissionGroupBeanVO);

    }
}

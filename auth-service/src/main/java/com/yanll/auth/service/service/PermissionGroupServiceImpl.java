package com.yanll.auth.service.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.auth.service.dao.PermissionGroupBeanMapper;
import com.yanll.auth.service.domain.PermissionGroupBean;
import com.yanll.auth.service.domain.PermissionGroupBeanDTO;
import com.yanll.framework.data.EntityConverter;
import com.yanll.framework.facade.exception.BizException;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class PermissionGroupServiceImpl implements IPermissionGroupService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionGroupServiceImpl.class);
    @Autowired
    PermissionGroupBeanMapper permissionGroupBeanMapper;


    @Override
    public PaginateWrapper<List<PermissionGroupBeanDTO>> selectPermissionGroups(Long portal_id, Pagination pagination) throws BizException {
        PageBounds pageBounds = new PageBounds(pagination.getPage(), pagination.getLimit());
        List<PermissionGroupBean> list_ = permissionGroupBeanMapper.selectPermissionGroups(portal_id, pageBounds);
        PaginateWrapper<List<PermissionGroupBeanDTO>> paginateWrapper = EntityConverter.toPaginateWrapper(list_, PermissionGroupBeanDTO.class, pageBounds);
        return paginateWrapper;
    }

    @Override
    public Integer selectCountByNameAndPortal(Long portal_id, String group_name) throws BizException {
        return permissionGroupBeanMapper.selectCountByNameAndPortal(portal_id, group_name);
    }

    @Override
    public Integer insertSelective(PermissionGroupBeanDTO permissionGroupBeanDTO) throws BizException {
        PermissionGroupBean permissionGroupBean = new PermissionGroupBean();
        EntityConverter.toPO(permissionGroupBeanDTO, permissionGroupBean);
        return permissionGroupBeanMapper.insertSelective(permissionGroupBean);
    }

    @Override
    public Integer updateByPrimaryKeySelective(PermissionGroupBeanDTO permissionGroupBeanDTO) throws BizException {
        PermissionGroupBean permissionGroupBean = new PermissionGroupBean();
        EntityConverter.toPO(permissionGroupBeanDTO, permissionGroupBean);
        return permissionGroupBeanMapper.updateByPrimaryKeySelective(permissionGroupBean);
    }

    @Override
    public Integer deleteByPrimaryKey(Long id) throws BizException {
        //todo-yll-fix 关联删除
        return permissionGroupBeanMapper.deleteByPrimaryKey(id);
    }
}

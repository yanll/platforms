package com.yanll.auth.service.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.auth.service.domain.PermissionGroupBeanDTO;
import com.yanll.auth.service.dao.PermissionGroupBeanMapper;
import com.yanll.auth.service.domain.PermissionGroupBean;
import com.yanll.framework.data.BaseMapper;
import com.yanll.framework.data.BaseServiceImpl;
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
public class PermissionGroupServiceImpl extends BaseServiceImpl<PermissionGroupBean, PermissionGroupBeanDTO> implements IPermissionGroupService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionGroupServiceImpl.class);
    @Autowired
    PermissionGroupBeanMapper permissionGroupBeanMapper;

    @Override
    public PaginateWrapper<List<PermissionGroupBeanDTO>> selectPermissionGroups(Long portal_id, Pagination pagination) throws BizException {
        PageBounds pageBounds = new PageBounds(pagination.getPage(), pagination.getLimit());
        List<PermissionGroupBean> list_ = permissionGroupBeanMapper.selectPermissionGroups(portal_id, pageBounds);
        PaginateWrapper<List<PermissionGroupBeanDTO>> list = toPaginateWrapper(list_, pageBounds);
        return list;
    }

    @Override
    public BaseMapper<PermissionGroupBean> getMapper() {
        return permissionGroupBeanMapper;
    }

    @Override
    public PermissionGroupBeanDTO getDTO() {
        return new PermissionGroupBeanDTO();
    }

    @Override
    public PermissionGroupBean getPOEntity() {
        return new PermissionGroupBean();
    }
}

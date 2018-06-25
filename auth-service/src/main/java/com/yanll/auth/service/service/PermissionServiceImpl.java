package com.yanll.auth.service.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.auth.service.dao.PermissionBeanMapper;
import com.yanll.auth.service.domain.PermissionBean;
import com.yanll.auth.service.domain.PermissionBeanDTO;
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
public class PermissionServiceImpl implements IPermissionService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
    @Autowired
    PermissionBeanMapper permissionBeanMapper;

    @Override
    public PaginateWrapper<List<PermissionBeanDTO>> selectPermissions(String system_code, Pagination pagination) throws BizException {
        PageBounds pageBounds = new PageBounds(pagination.getPage(), pagination.getLimit());
        List<PermissionBean> list_ = permissionBeanMapper.selectPermissions(system_code, pageBounds);
        PaginateWrapper<List<PermissionBeanDTO>> list = EntityConverter.toPaginateWrapper(list_, PermissionBeanDTO.class, pageBounds);
        return list;
    }

    @Override
    public List<PermissionBeanDTO> selectPermissions(Long role_id) {
        List<PermissionBean> list = permissionBeanMapper.selectRolePermissions(role_id);
        return EntityConverter.toDTOList(list, PermissionBeanDTO.class);
    }


    @Override
    public List<PermissionBeanDTO> selectPermissionsByUserId(Long user_id) {
        List<PermissionBean> list_ = permissionBeanMapper.selectPermissionsByUserId(user_id);
        List<PermissionBeanDTO> list = EntityConverter.toDTOList(list_, PermissionBeanDTO.class);
        return list;
    }

}

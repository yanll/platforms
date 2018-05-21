package com.yanll.auth.service.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.auth.service.dao.PermissionBeanMapper;
import com.yanll.auth.service.domain.PermissionBean;
import com.yanll.framework.data.BaseMapper;
import com.yanll.framework.data.BaseServiceImpl;
import com.yanll.framework.facade.exception.BizException;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;
import com.yanll.framework.util.UtilJackson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionBean, PermissionBeanDTO> implements IPermissionService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
    @Autowired
    PermissionBeanMapper permissionBeanMapper;

    @Override
    public List<PermissionBeanDTO> selectPermissions(Long portal_id) throws BizException {
        PageBounds pageBounds = new PageBounds(0, 100);
        List<PermissionBean> list = permissionBeanMapper.selectPermissions(portal_id, pageBounds);
        PageList<PermissionBean> list_ = (PageList<PermissionBean>) list;
        System.out.println(UtilJackson.toJSON(list_));
        System.out.println(UtilJackson.toJSON(list_.getPaginator()));
        return toVOList(list);
    }

    @Override
    public PaginateWrapper<List<PermissionBeanDTO>> selectPermissions(Long group_id, Pagination pagination) {
        PageBounds pageBounds = new PageBounds(pagination.getPage(), pagination.getLimit());
        List<PermissionBean> list_ = permissionBeanMapper.selectGroupPermissions(group_id, pageBounds);
        PaginateWrapper<List<PermissionBeanDTO>> list = toPaginateWrapper(list_, pageBounds);
        return list;
    }

    @Override
    public PermissionBean getPOEntity() {
        return new PermissionBean();
    }

    @Override
    public PermissionBeanDTO getDTO() {
        return new PermissionBeanDTO();
    }

    @Override
    public BaseMapper<PermissionBean> getMapper() {
        return permissionBeanMapper;
    }
}

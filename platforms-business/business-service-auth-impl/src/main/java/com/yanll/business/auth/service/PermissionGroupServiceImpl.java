package com.yanll.business.auth.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.dao.PermissionGroupBeanMapper;
import com.yanll.business.auth.domain.PermissionGroupBean;
import com.yanll.business.auth.domain.PermissionGroupBeanVO;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import com.yanll.framework.data.mysql.service.BaseServiceImpl;
import com.yanll.framework.util.exception.BizException;
import com.yanll.framework.util.page.PaginateWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class PermissionGroupServiceImpl extends BaseServiceImpl<PermissionGroupBean, PermissionGroupBeanVO> implements IPermissionGroupService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionGroupServiceImpl.class);
    @Autowired
    PermissionGroupBeanMapper permissionGroupBeanMapper;


    @Override
    public PaginateWrapper<List<PermissionGroupBeanVO>> selectPermissionGroups(PageBounds pageBounds) throws BizException {
        List<PermissionGroupBean> list_ = permissionGroupBeanMapper.selectPermissionGroups(pageBounds);
        PaginateWrapper<List<PermissionGroupBeanVO>> list = toPaginateWrapper(list_, pageBounds);
        return list;
    }

    @Override
    public BaseMapper<PermissionGroupBean> getMapper() {
        return permissionGroupBeanMapper;
    }

    @Override
    public PermissionGroupBeanVO getVO() {
        return new PermissionGroupBeanVO();
    }

    @Override
    public PermissionGroupBean getDataEntity() {
        return new PermissionGroupBean();
    }
}

package com.yanll.business.auth.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yanll.business.auth.dao.PermissionBeanMapper;
import com.yanll.business.auth.domain.PermissionBean;
import com.yanll.business.auth.domain.PermissionBeanVO;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import com.yanll.framework.data.mysql.service.BaseServiceImpl;
import com.yanll.framework.util.exception.BizException;
import com.yanll.framework.util.jackson.UtilJackson;
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
public class PermissionServiceImpl extends BaseServiceImpl<PermissionBean, PermissionBeanVO> implements IPermissionService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
    @Autowired
    PermissionBeanMapper permissionBeanMapper;

    @Override
    public List<PermissionBeanVO> selectPermissions(Long portal_id) throws BizException {
        PageBounds pageBounds = new PageBounds(0, 100);
        List<PermissionBean> list = permissionBeanMapper.selectPermissions(portal_id, pageBounds);
        PageList<PermissionBean> list_ = (PageList<PermissionBean>) list;
        System.out.println(UtilJackson.toJSON(list_));
        System.out.println(UtilJackson.toJSON(list_.getPaginator()));
        return toVOList(list);
    }


    @Override
    public PaginateWrapper<List<PermissionBeanVO>> selectPermissions(Long group_id, PageBounds pageBounds) {
        List<PermissionBean> list_ = permissionBeanMapper.selectGroupPermissions(group_id, pageBounds);
        PaginateWrapper<List<PermissionBeanVO>> list = toPaginateWrapper(list_, pageBounds);
        return list;
    }

    @Override
    public PermissionBean getDataEntity() {
        return new PermissionBean();
    }

    @Override
    public PermissionBeanVO getVO() {
        return new PermissionBeanVO();
    }

    @Override
    public BaseMapper<PermissionBean> getMapper() {
        return permissionBeanMapper;
    }
}

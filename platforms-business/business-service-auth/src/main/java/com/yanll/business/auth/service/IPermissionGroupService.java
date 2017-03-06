package com.yanll.business.auth.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.domain.PermissionBeanVO;
import com.yanll.business.auth.domain.PermissionGroupBeanVO;
import com.yanll.framework.data.mysql.service.BaseService;
import com.yanll.framework.util.exception.BizException;
import com.yanll.framework.util.page.PaginateWrapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by YANLL on 2016/11/17.
 */
public interface IPermissionGroupService extends BaseService<PermissionGroupBeanVO> {
    public PaginateWrapper<List<PermissionGroupBeanVO>> selectPermissionGroups(Integer portal_id, PageBounds pageBounds) throws BizException;

}

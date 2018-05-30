package com.yanll.auth.service.service;

import com.yanll.auth.service.domain.PermissionGroupBeanDTO;
import com.yanll.framework.facade.exception.BizException;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;

import java.util.List;

/**
 * Created by YANLL on 2016/11/17.
 */
public interface IPermissionGroupService {
    public PaginateWrapper<List<PermissionGroupBeanDTO>> selectPermissionGroups(Long portal_id, Pagination pagination) throws BizException;

    public Integer selectCountByNameAndPortal(Long portal_id, String group_name) throws BizException;


}

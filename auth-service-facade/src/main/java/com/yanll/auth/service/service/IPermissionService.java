package com.yanll.auth.service.service;

import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.framework.facade.IBaseService;
import com.yanll.framework.facade.exception.BizException;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;

import java.util.List;

/**
 * Created by YANLL on 2016/11/17.
 */
public interface IPermissionService extends IBaseService<PermissionBeanDTO> {
    public List<PermissionBeanDTO> selectPermissions(Long portal_id) throws BizException;

    public PaginateWrapper<List<PermissionBeanDTO>> selectPermissions(Long group_id, Pagination pagination) throws BizException;

}

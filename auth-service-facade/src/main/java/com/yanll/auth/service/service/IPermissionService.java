package com.yanll.auth.service.service;

import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.framework.facade.exception.BizException;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;

import java.util.List;

/**
 * Created by YANLL on 2016/11/17.
 */
public interface IPermissionService {
    public List<PermissionBeanDTO> selectPermissions(Long role_id) throws BizException;

    public PaginateWrapper<List<PermissionBeanDTO>> selectPermissions(String system_code, Pagination pagination) throws BizException;

    public List<PermissionBeanDTO> selectPermissionsByUserId(Long user_id);
}

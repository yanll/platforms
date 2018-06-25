package com.yanll.auth.service.service;

import com.yanll.auth.service.domain.RoleBeanDTO;
import com.yanll.framework.facade.exception.BizException;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;

import java.util.List;

/**
 * Created by YANLL on 2016/11/17.
 */
public interface IRoleService {
    public PaginateWrapper<List<RoleBeanDTO>> selectRoles(String system_code, Pagination pagination) throws BizException;

    public Integer selectCountByNameAndSystem(String system_code, String role_name) throws BizException;

    public Integer insertSelective(RoleBeanDTO roleBeanDTO) throws BizException;

    public Integer updateByPrimaryKeySelective(RoleBeanDTO roleBeanDTO) throws BizException;

    public Integer deleteByPrimaryKey(Long id) throws BizException;


}

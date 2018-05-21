package com.yanll.auth.service.service;

import com.yanll.auth.service.domain.UserBeanDTO;
import com.yanll.framework.facade.IBaseService;
import com.yanll.framework.facade.exception.BizException;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;

import java.util.List;

/**
 * Created by YANLL on 2016/11/17.
 */
public interface IUserService extends IBaseService<UserBeanDTO> {
    public UserBeanDTO selectUser(String username, String password) throws BizException;

    public PaginateWrapper<List<UserBeanDTO>> selectUsers(Pagination pagination) throws BizException;

}

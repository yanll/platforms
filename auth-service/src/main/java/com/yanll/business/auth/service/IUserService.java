package com.yanll.business.auth.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.domain.UserBeanVO;
import com.yanll.framework.data.mysql.service.BaseService;
import com.yanll.framework.util.exception.BizException;
import com.yanll.framework.util.page.PaginateWrapper;

import java.util.List;

/**
 * Created by YANLL on 2016/11/17.
 */
public interface IUserService extends BaseService<UserBeanVO> {
    public UserBeanVO selectUser(String username, String password) throws BizException;

    public PaginateWrapper<List<UserBeanVO>> selectUsers(PageBounds pageBounds) throws BizException;

    public Integer batchInsertFromExcel(List<UserBeanVO> list) throws BizException;
}

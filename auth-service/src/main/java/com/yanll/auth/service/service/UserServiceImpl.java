package com.yanll.auth.service.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.auth.service.dao.PermissionBeanMapper;
import com.yanll.auth.service.dao.UserBeanMapper;
import com.yanll.auth.service.domain.UserBean;
import com.yanll.auth.service.domain.UserBeanDTO;
import com.yanll.framework.data.EntityConverter;
import com.yanll.framework.facade.exception.BizCode;
import com.yanll.framework.facade.exception.BizException;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserBeanMapper userBeanMapper;
    @Autowired
    PermissionBeanMapper permissionBeanMapper;

    @Override
    public UserBeanDTO selectUser(String username, String password) {
        UserBean userBean = userBeanMapper.selectUser(username, password);
        if (userBean == null || userBean.getId() == null)
            throw new BizException(BizCode.LOGIN_FAILD.getValue(), BizCode.LOGIN_FAILD.getDesc());


        return null;// toDTO(userBean);
    }

    @Override
    public PaginateWrapper<List<UserBeanDTO>> selectUsers(Pagination pagination) {
        PageBounds pageBounds = new PageBounds(pagination.getPage(), pagination.getLimit());
        List<UserBean> list_ = userBeanMapper.selectUsers(pageBounds);
        PaginateWrapper<List<UserBeanDTO>> list = EntityConverter.toPaginateWrapper(list_, UserBeanDTO.class, pageBounds);
        return list;
    }

}
package com.yanll.business.auth.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yanll.business.auth.dao.UserBeanMapper;
import com.yanll.business.auth.domain.PermissionGroupBean;
import com.yanll.business.auth.domain.PermissionGroupBeanVO;
import com.yanll.business.auth.domain.UserBean;
import com.yanll.business.auth.domain.UserBeanVO;
import com.yanll.framework.data.mysql.service.BaseServiceImpl;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import com.yanll.framework.util.exception.BizCode;
import com.yanll.framework.util.exception.BizException;
import com.yanll.framework.util.page.PaginateWrapper;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserBean, UserBeanVO> implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserBeanMapper userBeanMapper;


    @Override
    public UserBeanVO selectUser(String username, String password) {
        UserBean userBean = userBeanMapper.selectUser(username, password);
        if (userBean == null || userBean.getId() == null)
            throw new BizException(BizCode.LOGIN_FAILD.getValue(), BizCode.LOGIN_FAILD.getDesc());
        return toVO(userBean);
    }

    @Override
    public PaginateWrapper<List<UserBeanVO>> selectUsers(PageBounds pageBounds) {
        List<UserBean> list_ = userBeanMapper.selectUsers(pageBounds);
        PaginateWrapper<List<UserBeanVO>> list = toPaginateWrapper(list_, pageBounds);
        return list;
    }

    @Override
    public Integer batchInsertFromExcel(List<UserBeanVO> list) {
        List<UserBean> rs = toDOList(list);
        return userBeanMapper.batchInsert(rs);
    }

    @Override
    public UserBean getDataEntity() {
        return new UserBean();
    }

    @Override
    public UserBeanVO getVO() {
        return new UserBeanVO();
    }

    @Override
    public BaseMapper<UserBean> getMapper() {
        return userBeanMapper;
    }
}

package com.yanll.auth.service.dao;

import com.yanll.auth.service.domain.UserBean;
import com.yanll.framework.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface UserBeanMapper extends BaseMapper<UserBean> {

    public UserBean selectUser(@Param("username") String username, @Param("password") String password);

    public List<UserBean> selectUsers(RowBounds rowBounds);

}
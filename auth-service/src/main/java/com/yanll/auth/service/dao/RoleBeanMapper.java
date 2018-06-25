package com.yanll.auth.service.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.auth.service.domain.RoleBean;
import com.yanll.framework.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleBeanMapper extends BaseMapper<RoleBean> {

    public List<RoleBean> selectRoles(@Param("system_code") String system_code, PageBounds pageBounds);

    public Integer selectCountByNameAndSystem(@Param("system_code") String system_code, @Param("role_name") String role_name);
}
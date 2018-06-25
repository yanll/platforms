package com.yanll.auth.service.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.auth.service.domain.RoleBean;
import com.yanll.framework.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionGroupBeanMapper extends BaseMapper<RoleBean> {

    public List<RoleBean> selectRoles(@Param("system_code") String system_code, PageBounds pageBounds);

    public Integer selectCountByNameAndPortal(@Param("system_code") String system_code, @Param("group_name") String group_name);
}
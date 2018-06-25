package com.yanll.auth.service.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.auth.service.domain.PermissionBean;
import com.yanll.framework.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionBeanMapper extends BaseMapper<PermissionBean> {

    public List<PermissionBean> selectPermissions(@Param("system_code") String system_code, PageBounds pageBounds);

    public List<PermissionBean> selectGroupPermissions(@Param("role_id") Long role_id);

    public List<PermissionBean> selectPermissionsByUserId(@Param("user_id") Long user_id);

}
package com.yanll.auth.service.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.auth.service.domain.PermissionBean;
import com.yanll.framework.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionBeanMapper extends BaseMapper<PermissionBean> {

    public List<PermissionBean> selectPermissions(@Param("portal_id") Long portal_id, PageBounds pageBounds);

    public List<PermissionBean> selectGroupPermissions(@Param("group_id") Long group_id, PageBounds pageBounds);

    public List<PermissionBean> selectPermissionsByUserId(@Param("user_id") Long user_id);

}
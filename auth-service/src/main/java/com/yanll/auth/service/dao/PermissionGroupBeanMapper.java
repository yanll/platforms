package com.yanll.auth.service.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.auth.service.domain.PermissionGroupBean;
import com.yanll.framework.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionGroupBeanMapper extends BaseMapper<PermissionGroupBean> {

    public List<PermissionGroupBean> selectPermissionGroups(@Param("portal_id") Long portal_id, PageBounds pageBounds);

    public Integer selectCountByNameAndPortal(@Param("portal_id") Long portal_id, @Param("group_name") String group_name);
}
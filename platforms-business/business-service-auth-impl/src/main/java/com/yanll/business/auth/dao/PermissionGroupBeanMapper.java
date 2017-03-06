package com.yanll.business.auth.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.domain.PermissionGroupBean;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionGroupBeanMapper extends BaseMapper<PermissionGroupBean> {
    public List<PermissionGroupBean> selectPermissionGroups(@Param("portal_id") Integer portal_id, PageBounds pageBounds);

}
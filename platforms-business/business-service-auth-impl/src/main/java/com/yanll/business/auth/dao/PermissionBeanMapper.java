package com.yanll.business.auth.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.domain.PermissionBean;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionBeanMapper extends BaseMapper<PermissionBean> {

    public List<PermissionBean> selectPermissions(PageBounds pageBounds);

}
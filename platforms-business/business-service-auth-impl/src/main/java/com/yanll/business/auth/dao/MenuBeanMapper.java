package com.yanll.business.auth.dao;

import com.yanll.business.auth.domain.MenuBean;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuBeanMapper extends BaseMapper<MenuBean> {
    public List<Map<String, Object>> selectAllMapMenusForTree(@Param("portal_id") Long portal_id);
}
package com.yanll.auth.service.dao;

import com.yanll.auth.service.domain.MenuBean;
import com.yanll.framework.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuBeanMapper extends BaseMapper<MenuBean> {
    public List<Map<String, Object>> selectAllMapMenusForTree(@Param("system_code") String system_code);

    public List<Map<String, Object>> selectMenusByIds(@Param("system_code") String system_code, @Param("ids") List<Long> ids);
}
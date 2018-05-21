package com.yanll.auth.service.service;

import com.yanll.auth.service.domain.MenuBeanDTO;
import com.yanll.auth.service.dao.MenuBeanMapper;
import com.yanll.auth.service.domain.MenuBean;
import com.yanll.framework.data.BaseMapper;
import com.yanll.framework.data.BaseServiceImpl;
import com.yanll.framework.util.TreeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuBean, MenuBeanDTO> implements IMenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    MenuBeanMapper menuBeanMapper;

    @Override
    public List<Map<String, Object>> selectMapTreeMenus(Long portal_id) {
        List<Map<String, Object>> list_ = menuBeanMapper.selectAllMapMenusForTree(portal_id);
        List<Map<String, Object>> list = TreeUtil.buildMapTree(list_, "id", "parent_id");
        return list;
    }

    @Override
    public MenuBean getPOEntity() {
        return new MenuBean();
    }

    @Override
    public MenuBeanDTO getDTO() {
        return new MenuBeanDTO();
    }

    @Override
    public BaseMapper<MenuBean> getMapper() {
        return menuBeanMapper;
    }
}

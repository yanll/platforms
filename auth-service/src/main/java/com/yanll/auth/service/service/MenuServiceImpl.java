package com.yanll.auth.service.service;

import com.yanll.auth.service.dao.MenuBeanMapper;
import com.yanll.framework.util.TreeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class MenuServiceImpl implements IMenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    MenuBeanMapper menuBeanMapper;

    @Override
    public List<Map<String, Object>> selectMapTreeMenus(Long portal_id) {
        List<Map<String, Object>> list_ = menuBeanMapper.selectAllMapMenusForTree(portal_id);
        if (list_ == null || list_.size() == 0) return new ArrayList<>();
        List<Map<String, Object>> list = TreeUtil.buildMapTree(list_, "id", "parent_id");
        return list;
    }

    @Override
    public List<Map<String, Object>> selectMapMenus(Long portal_id, List<Long> ids) {
        List<Map<String, Object>> list_ = menuBeanMapper.selectMenusByIds(portal_id, ids);
        if (list_ == null || list_.size() == 0) return new ArrayList<>();
        return list_;
    }


}

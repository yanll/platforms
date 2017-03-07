package com.yanll.business.auth.service;

import com.yanll.business.auth.dao.MenuBeanMapper;
import com.yanll.business.auth.domain.MenuBean;
import com.yanll.business.auth.domain.MenuBeanVO;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import com.yanll.framework.data.mysql.service.BaseServiceImpl;
import com.yanll.framework.util.TreeUtil;
import com.yanll.framework.util.enums.IEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuBean, MenuBeanVO> implements IMenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    MenuBeanMapper menuBeanMapper;

    public List<Map<String, Object>> selectMapTreeMenus() {
        List<Map<String, Object>> list_ = menuBeanMapper.selectAllMapMenusForTree();
        List<Map<String, Object>> menu_list = new ArrayList<>();
        IEnum.SYSTEM_PORTAL[] portals = IEnum.SYSTEM_PORTAL.values();
        for (IEnum.SYSTEM_PORTAL portal : portals) {
            Map<String, Object> map = new HashMap();
            map.put("id", portal.getValue());
            map.put("menu_name", portal.getDesc());
            map.put("editable", IEnum.YESNO.N.getValue());
            menu_list.add(map);
            for (Map<String, Object> row : list_) {
                if (row.get("portal_id") != null && row.get("portal_id").toString().equals(map.get("id").toString()))
                    row.put("parent_id", map.get("id"));
            }
        }
        menu_list.addAll(list_);
        List<Map<String, Object>> list = TreeUtil.buildMapTree(menu_list, "id", "parent_id");
        return list;
    }

    @Override
    public MenuBean getDataEntity() {
        return new MenuBean();
    }

    @Override
    public MenuBeanVO getVO() {
        return new MenuBeanVO();
    }

    @Override
    public BaseMapper<MenuBean> getMapper() {
        return menuBeanMapper;
    }
}

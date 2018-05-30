package com.yanll.auth.console.manager;

import com.yanll.auth.service.domain.MenuBeanDTO;
import com.yanll.auth.service.service.IMenuService;
import com.yanll.framework.facade.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by YANLL on 2016/12/7.
 */
@Component
public class MenuManager {
    private static final Logger logger = LoggerFactory.getLogger(MenuManager.class);
    @Autowired
    IMenuService menuService;

    public void save(MenuBeanDTO menu) {
        if (menu == null) throw new BizException("菜单对象不能为空！");
        //menuService.insertSelective(menu);
    }

    public void update(MenuBeanDTO menu) {
        if (menu == null) throw new BizException("菜单对象不能为空！");
        if (menu.getId() == null) throw new BizException("主键不能为空！");
        //MenuBeanDTO vo = menuService.selectByPrimaryKey(menu.getId());
        //if (vo == null) return;
       // menuService.updateByPrimaryKeySelective(menu);
    }

    public void delete(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        //MenuBeanDTO vo = menuService.selectByPrimaryKey(id);
      //  if (vo == null) return;
        //menuService.deleteByPrimaryKey(id);
    }


    public MenuBeanDTO detail(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        return null;//menuService.selectByPrimaryKey(id);
    }

    public List<Map<String, Object>> selectMapTreeMenus(Long portal_id) {
        return menuService.selectMapTreeMenus(portal_id);
    }
}

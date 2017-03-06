package com.yanll.console.auth.manager;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.domain.MenuBeanVO;
import com.yanll.business.auth.service.IMenuService;
import com.yanll.framework.util.enums.IEnum;
import com.yanll.framework.util.exception.BizException;
import com.yanll.framework.util.page.PaginateWrapper;
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


    public void save(MenuBeanVO menu) {
        if (menu == null) throw new BizException("菜单对象不能为空！");
        if (menu.getId() != null && menu.getId() > 0) menuService.updateByPrimaryKeySelective(menu);
        else menuService.insertSelective(menu);

    }

    public void delete(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        menuService.deleteByPrimaryKey(id);
    }

    public PaginateWrapper<List<MenuBeanVO>> getMenus(PageBounds pageBounds) {
        return menuService.selectMenus(IEnum.SYSTEM_PORTAL.AUTH_CONSOLE.getValue(), pageBounds);
    }

    public List<Map<String, Object>> selectMapTreeMenus() {
        return menuService.selectMapTreeMenus();
    }
}

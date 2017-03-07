package com.yanll.console.auth.manager;

import com.yanll.business.auth.domain.MenuBeanVO;
import com.yanll.business.auth.domain.PermissionGroupBeanVO;
import com.yanll.business.auth.service.IMenuService;
import com.yanll.framework.util.enums.IEnum;
import com.yanll.framework.util.exception.BizException;
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
        menuService.insertSelective(menu);
    }

    public void update(MenuBeanVO menu) {
        if (menu == null) throw new BizException("菜单对象不能为空！");
        if (menu.getId() == null) throw new BizException("主键不能为空！");
        MenuBeanVO vo = menuService.selectByPrimaryKey(menu.getId());
        if (vo == null || vo.getEnabled().intValue() == IEnum.YESNO.N.getValue().intValue()) return;
        menuService.updateByPrimaryKeySelective(menu);
    }

    public void delete(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        MenuBeanVO vo = menuService.selectByPrimaryKey(id);
        if (vo == null || vo.getEnabled().intValue() == IEnum.YESNO.N.getValue().intValue()) return;
        menuService.deleteByPrimaryKey(id);
    }


    public MenuBeanVO detail(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        return menuService.selectByPrimaryKey(id);
    }

    public List<Map<String, Object>> selectMapTreeMenus() {
        return menuService.selectMapTreeMenus();
    }
}

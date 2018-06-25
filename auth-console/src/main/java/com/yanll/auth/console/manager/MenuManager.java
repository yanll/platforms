package com.yanll.auth.console.manager;

import com.yanll.auth.service.domain.MenuBean;
import com.yanll.auth.service.domain.MenuBeanDTO;
import com.yanll.auth.service.service.IMenuService;
import com.yanll.framework.data.EntityConverter;
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

    public void save(MenuBeanDTO menuBeanDTO) {
        if (menuBeanDTO == null) throw new BizException("菜单对象不能为空！");
        MenuBean menuBean = new MenuBean();
        EntityConverter.toPO(menuBeanDTO, menuBean);
        /*menuService.insertSelective(menuBean);*/
    }

    public void update(MenuBeanDTO menuBeanDTO) {
        if (menuBeanDTO == null) throw new BizException("菜单对象不能为空！");
        if (menuBeanDTO.getId() == null) throw new BizException("主键不能为空！");
        MenuBean menuBean = new MenuBean();
        EntityConverter.toPO(menuBeanDTO, menuBean);
        /*menuService.updateByPrimaryKeySelective(menuBean);*/
    }

    public void delete(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
    }


    public MenuBeanDTO detail(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        return null;//menuService.selectByPrimaryKey(id);
    }

    public List<Map<String, Object>> selectMapTreeMenus(String system_code) {
        return menuService.selectMapTreeMenus(system_code);
    }
}

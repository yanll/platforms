package com.yanll.console.auth.controller;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.domain.MenuBeanVO;
import com.yanll.console.auth.manager.MenuManager;
import com.yanll.framework.util.exception.BizCode;
import com.yanll.framework.util.page.PaginateWrapper;
import com.yanll.framework.util.page.PaginationUtil;
import com.yanll.framework.web.result.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * Created by YANLL on 2016/08/29.
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    MenuManager menuManager;

    @RequestMapping(value = "/list", method = RequestMethod.GET, name = "查询菜单列表")
    @ResponseBody
    public JSON<PaginateWrapper<List<MenuBeanVO>>> list(Integer page, Integer limit) {
        PageBounds pageBounds = PaginationUtil.toPageBounds(page, limit, true);
        menuManager.selectMapTreeMenus();
        return new JSON(BizCode.OK.getValue(), menuManager.getMenus(pageBounds));
    }

    @RequestMapping(value = "/map_tree", method = RequestMethod.GET, name = "查询树形菜单集合")
    @ResponseBody
    public JSON<List<Map<String, Object>>> map_tree() {
        List<Map<String, Object>> list = menuManager.selectMapTreeMenus();
        return new JSON(BizCode.OK.getValue(), list);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, name = "保存菜单")
    @ResponseBody
    public JSON save(@RequestBody MenuBeanVO menu) {
        menuManager.save(menu);
        return new JSON(BizCode.OK.getValue());
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, name = "删除菜单")
    @ResponseBody
    public JSON delete(@PathVariable Long id) {
        menuManager.delete(id);
        return new JSON(BizCode.OK.getValue());
    }

}


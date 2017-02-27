package com.yanll.console.auth.controller;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.yanll.business.auth.domain.MenuBeanVO;
import com.yanll.console.auth.manager.MenuManager;
import com.yanll.framework.util.exception.BizCode;
import com.yanll.framework.util.page.PageUtil;
import com.yanll.framework.util.page.PaginateWrapper;
import com.yanll.framework.web.result.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public JSON<PaginateWrapper<List<MenuBeanVO>>> menus(Integer page, Integer limit) {
        PageBounds pageBounds = PageUtil.toPageBounds(page, limit);
        return new JSON(BizCode.OK.getValue(), menuManager.getMenus(pageBounds));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, name = "保存菜单")
    @ResponseBody
    public JSON save(@RequestBody MenuBeanVO menu) {
        menuManager.save(menu);
        return new JSON(BizCode.OK.getValue());
    }

}


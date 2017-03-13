package com.yanll.console.auth.controller;


import com.yanll.console.auth.manager.MenuManager;
import com.yanll.framework.util.exception.BizCode;
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

    @RequestMapping(value = "/tree/{portal_id}", method = RequestMethod.GET, name = "查询树形菜单集合")
    @ResponseBody
    public JSON<List<Map<String, Object>>> tree(@PathVariable Long portal_id) {
        List<Map<String, Object>> list = menuManager.selectMapTreeMenus(  portal_id);
        return new JSON(BizCode.OK.getValue(), list);
    }

}


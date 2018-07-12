package com.yanll.auth.console.controller;


import com.yanll.auth.console.manager.MenuManager;
import com.yanll.framework.facade.domain.AjaxResult;
import com.yanll.framework.facade.exception.BizCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.techforge.patron.Permissions;

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

    @Permissions(required = false)
    @RequestMapping(value = "/tree/{system_code}", method = RequestMethod.GET, name = "查询树形菜单集合")
    public AjaxResult<List<Map<String, Object>>> tree(@PathVariable String system_code) {
        List<Map<String, Object>> list = menuManager.selectMapTreeMenus(system_code);
        return new AjaxResult(BizCode.OK.getValue(), list);
    }

}


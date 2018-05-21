package com.yanll.auth.service.service;

import com.yanll.auth.service.domain.MenuBeanDTO;
import com.yanll.framework.facade.IBaseService;
import com.yanll.framework.facade.exception.BizException;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/17.
 */
public interface IMenuService extends IBaseService<MenuBeanDTO> {
    public List<Map<String, Object>> selectMapTreeMenus(Long portal_id) throws BizException;

}

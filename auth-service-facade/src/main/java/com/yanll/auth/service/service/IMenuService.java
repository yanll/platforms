package com.yanll.auth.service.service;

import com.yanll.framework.facade.exception.BizException;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/17.
 */
public interface IMenuService {
    public List<Map<String, Object>> selectMapTreeMenus(Long portal_id) throws BizException;

    public List<Map<String, Object>> selectAllMenus(Long portal_id) throws BizException;

    public List<Map<String, Object>> selectMapMenus(Long portal_id, List<Long> ids) throws BizException;

}

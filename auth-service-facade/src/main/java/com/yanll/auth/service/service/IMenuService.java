package com.yanll.auth.service.service;

import com.yanll.framework.facade.exception.BizException;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/17.
 */
public interface IMenuService {
    public List<Map<String, Object>> selectMapTreeMenus(String system_code) throws BizException;

    public List<Map<String, Object>> selectAllMenus(String system_code) throws BizException;

    public List<Map<String, Object>> selectMapMenus(String system_code, List<Long> ids) throws BizException;

}

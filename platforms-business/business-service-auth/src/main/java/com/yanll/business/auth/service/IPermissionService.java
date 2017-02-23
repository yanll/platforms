package com.yanll.business.auth.service;

import com.yanll.business.auth.domain.PermissionBeanVO;
import com.yanll.framework.data.mysql.service.BaseService;
import com.yanll.framework.util.exception.BizException;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
public interface IPermissionService extends BaseService<PermissionBeanVO> {
    public List<PermissionBeanVO> selectPermissions() throws BizException;
}

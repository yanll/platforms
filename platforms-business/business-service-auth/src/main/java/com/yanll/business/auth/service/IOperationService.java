package com.yanll.business.auth.service;

import com.yanll.business.auth.domain.OperationBeanVO;
import com.yanll.framework.data.mysql.service.BaseService;
import com.yanll.framework.util.exception.BizException;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
public interface IOperationService extends BaseService<OperationBeanVO> {
    public List<OperationBeanVO> selectOperations() throws BizException;
}

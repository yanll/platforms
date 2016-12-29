package com.yanll.business.auth.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.dao.OperationBeanMapper;
import com.yanll.business.auth.domain.OperationBean;
import com.yanll.business.auth.domain.OperationBeanVO;
import com.yanll.framework.core.service.mysql.BaseServiceImpl;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import com.yanll.framework.util.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class OperationServiceImpl extends BaseServiceImpl<OperationBean, OperationBeanVO> implements IOperationService {

    private static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);
    @Autowired
    OperationBeanMapper operationBeanMapper;

    @Override
    public List<OperationBeanVO> selectOperations() throws BizException {
        return toVOList(operationBeanMapper.selectOperations(new PageBounds(1, 2)));
    }

    @Override
    public OperationBean getDataEntity() {
        return new OperationBean();
    }

    @Override
    public OperationBeanVO getVO() {
        return new OperationBeanVO();
    }

    @Override
    public BaseMapper<OperationBean> getMapper() {
        return operationBeanMapper;
    }
}

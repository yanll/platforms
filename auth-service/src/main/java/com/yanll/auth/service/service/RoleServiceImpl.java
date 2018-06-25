package com.yanll.auth.service.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.auth.service.dao.RoleBeanMapper;
import com.yanll.auth.service.domain.RoleBean;
import com.yanll.auth.service.domain.RoleBeanDTO;
import com.yanll.framework.data.EntityConverter;
import com.yanll.framework.facade.exception.BizException;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class RoleServiceImpl implements IRoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    RoleBeanMapper roleBeanMapper;


    @Override
    public PaginateWrapper<List<RoleBeanDTO>> selectRoles(String system_code, Pagination pagination) throws BizException {
        PageBounds pageBounds = new PageBounds(pagination.getPage(), pagination.getLimit());
        List<RoleBean> list_ = roleBeanMapper.selectRoles(system_code, pageBounds);
        PaginateWrapper<List<RoleBeanDTO>> paginateWrapper = EntityConverter.toPaginateWrapper(list_, RoleBeanDTO.class, pageBounds);
        return paginateWrapper;
    }

    @Override
    public Integer selectCountByNameAndSystem(String system_code, String role_name) throws BizException {
        return roleBeanMapper.selectCountByNameAndSystem(system_code, role_name);
    }

    @Override
    public Integer insertSelective(RoleBeanDTO roleBeanDTO) throws BizException {
        RoleBean roleBean = new RoleBean();
        EntityConverter.toPO(roleBeanDTO, roleBean);
        return roleBeanMapper.insertSelective(roleBean);
    }

    @Override
    public Integer updateByPrimaryKeySelective(RoleBeanDTO roleBeanDTO) throws BizException {
        RoleBean roleBean = new RoleBean();
        EntityConverter.toPO(roleBeanDTO, roleBean);
        return roleBeanMapper.updateByPrimaryKeySelective(roleBean);
    }

    @Override
    public Integer deleteByPrimaryKey(Long id) throws BizException {
        //todo-yll-fix 关联删除
        return roleBeanMapper.deleteByPrimaryKey(id);
    }
}

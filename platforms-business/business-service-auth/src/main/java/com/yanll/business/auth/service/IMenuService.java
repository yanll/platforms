package com.yanll.business.auth.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yanll.business.auth.domain.MenuBeanVO;
import com.yanll.framework.data.mysql.service.BaseService;
import com.yanll.framework.util.exception.BizException;
import com.yanll.framework.util.page.PaginateWrapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/17.
 */
public interface IMenuService extends BaseService<MenuBeanVO> {
    public List<Map<String, Object>> selectMapTreeMenus(Long portal_id) throws BizException;

}

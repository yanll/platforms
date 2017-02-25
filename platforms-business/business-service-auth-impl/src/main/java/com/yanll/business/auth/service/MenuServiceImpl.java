package com.yanll.business.auth.service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;
import com.yanll.business.auth.dao.MenuBeanMapper;
import com.yanll.business.auth.domain.MenuBean;
import com.yanll.business.auth.domain.MenuBeanVO;
import com.yanll.framework.data.mysql.service.BaseServiceImpl;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import com.yanll.framework.util.exception.BizException;
import com.yanll.framework.util.jackson.UtilJackson;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuBean, MenuBeanVO> implements IMenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    MenuBeanMapper menuBeanMapper;

    @Override
    public List<MenuBeanVO> selectMenus() throws BizException {
//        PageRowBounds pageRowBounds = new PageRowBounds(0, 100);
//        List<MenuBean> list_ = menuBeanMapper.selectMenus(pageRowBounds);
//        //Page<MenuBean> page = (Page) list_;
//        List<MenuBeanVO> list = toVOList(list_);
//        System.out.println(UtilJackson.toJSON(pageRowBounds));
//        //System.out.println(UtilJackson.toJSON(page.getTotal()));
//        return list;
        RowBounds rowBounds = new RowBounds(0, 5);
        List<MenuBean> list_ = menuBeanMapper.selectMenus(rowBounds);
        PageList<MenuBean> p = (PageList<MenuBean>) list_;
        System.out.println(UtilJackson.toJSON(rowBounds));
        System.out.println(UtilJackson.toJSON(p));
        List<MenuBeanVO> list = toVOList(list_);
        return list;
    }

    @Override
    public MenuBean getDataEntity() {
        return new MenuBean();
    }

    @Override
    public MenuBeanVO getVO() {
        return new MenuBeanVO();
    }

    @Override
    public BaseMapper<MenuBean> getMapper() {
        return menuBeanMapper;
    }
}

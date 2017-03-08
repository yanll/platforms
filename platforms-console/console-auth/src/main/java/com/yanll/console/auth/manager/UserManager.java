package com.yanll.console.auth.manager;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.h2finance.framework.util.enums.IEnum;
import com.yanll.business.auth.domain.UserBeanVO;
import com.yanll.business.auth.service.IUserService;
import com.yanll.framework.util.exception.BizException;
import com.yanll.framework.util.importor.ExcelImportHandler;
import com.yanll.framework.util.jackson.UtilJackson;
import com.yanll.framework.util.page.PaginateWrapper;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by YANLL on 2016/12/7.
 */
@Component
public class UserManager {
    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);
    @Autowired
    IUserService userService;

    @Autowired
    ExcelImportHandler<UserBeanVO> excelImportPreHandler;

    public void imp(MultipartFile file) {
        if (file == null || file.isEmpty()) throw new BizException("文件不能为空！");
        String filename = file.getOriginalFilename();
        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            logger.error("用户导入-文件流读取失败！");
            throw new BizException("文件流读取失败！");
        }
        List<UserBeanVO> pre_list = excelImportPreHandler.handle(null, filename, is, (error_index, values) -> {
            UserBeanVO vo = new UserBeanVO();
            vo.setId(Long.parseLong(values[0]));
            vo.setUsername(values[1]);
            vo.setNickname(values[2]);
            vo.setPassword("pwd");
            if (vo.getUsername().length() > 6) {
                throw new BizException("第" + error_index + "行第" + 2 + "列：名称长度不能大于6个字符！");
            }
            System.out.println(UtilJackson.toJSON(vo));
            return vo;
        });
        Integer i = userService.batchInsertFromExcel(pre_list);
        System.out.println(i);
    }

    public void save(UserBeanVO user) {
        if (user == null) throw new BizException("用户对象不能为空！");
        if (user.getEnabled() == null) user.setEnabled(IEnum.YESNO.N.getValue());
        if (Strings.isNullOrEmpty(user.getUsername())) throw new BizException("用户名不能为空！");
        if (Strings.isNullOrEmpty(user.getNickname())) throw new BizException("昵称不能为空！");
        //默认密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getUsername() + "_123456"));
        userService.insertSelective(user);
    }

    public void del(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        userService.deleteByPrimaryKey(id);
    }

    public void update(UserBeanVO userVO) {
        if (userVO == null) throw new BizException("用户对象不能为空！");
        if (userVO.getId() == null) throw new BizException("主键不能为空！");
        UserBeanVO user = new UserBeanVO();
        user.setId(userVO.getId());
        user.setNickname(userVO.getNickname());
        user.setEnabled(userVO.getEnabled());
        userService.updateByPrimaryKeySelective(user);
    }

    public UserBeanVO getUser(Long user_id) {
        return userService.selectByPrimaryKey(user_id);
    }

    public PaginateWrapper<List<UserBeanVO>> getUsers(PageBounds pageBounds) {
        return userService.selectUsers(pageBounds);
    }

}

package com.yanll.auth.console.manager;

import com.google.common.base.Strings;
import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.auth.service.domain.UserBeanDTO;
import com.yanll.auth.service.service.IMenuService;
import com.yanll.auth.service.service.IPermissionService;
import com.yanll.auth.service.service.IUserService;
import com.yanll.framework.facade.exception.BizException;
import com.yanll.framework.facade.page.PaginateWrapper;
import com.yanll.framework.facade.page.Pagination;
import com.yanll.framework.util.UtilJackson;
import com.yanll.framework.util.enums.IEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

//import com.yanll.framework.util.importor.ExcelImportHandler;

/**
 * Created by YANLL on 2016/12/7.
 */
@Component
public class UserManager {
    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);
    @Autowired
    IUserService userService;
    @Autowired
    IPermissionService permissionService;
    @Autowired
    IMenuService menuService;

//    @Autowired
//    ExcelImportHandler<UserBeanDTO> excelImportPreHandler;

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
        /*List<UserBeanDTO> pre_list = excelImportPreHandler.handle(null, filename, is, (error_index, values) -> {
            UserBeanDTO vo = new UserBeanDTO();
            vo.setId(Long.parseLong(values[0]));
            vo.setUsername(values[1]);
            vo.setNickname(values[2]);
            vo.setPassword("pwd");
            if (vo.getUsername().length() > 6) {
                throw new BizException("第" + error_index + "行第" + 2 + "列：名称长度不能大于6个字符！");
            }
            System.out.println(UtilJackson.toJSON(vo));
            return vo;
        });*/
//        Integer i = userService.batchInsertFromExcel(pre_list);
//        System.out.println(i);
    }

    public void save(UserBeanDTO user) {
        if (user == null) throw new BizException("用户对象不能为空！");
        if (user.getEnabled() == null) user.setEnabled(IEnum.YESNO.N.getValue());
        if (Strings.isNullOrEmpty(user.getUsername())) throw new BizException("用户名不能为空！");
        if (Strings.isNullOrEmpty(user.getNickname())) throw new BizException("昵称不能为空！");
        //默认密码
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //user.setPassword(encoder.encode(user.getUsername() + "_123456"));
        //userService.insertSelective(user);
    }

    public void del(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        //userService.deleteByPrimaryKey(id);
    }

    public void update(UserBeanDTO userVO) {
        if (userVO == null) throw new BizException("用户对象不能为空！");
        if (userVO.getId() == null) throw new BizException("主键不能为空！");
        UserBeanDTO user = new UserBeanDTO();
        user.setId(userVO.getId());
        user.setNickname(userVO.getNickname());
        user.setEnabled(userVO.getEnabled());
        //userService.updateByPrimaryKeySelective(user);
    }

    public void updatePwd(Long id) {
        if (id == null) throw new BizException("主键不能为空！");
        UserBeanDTO user = new UserBeanDTO();
        user.setId(id);
        //默认密码
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        user.setPassword(encoder.encode(user.getUsername() + "_123456"));
        //userService.updateByPrimaryKeySelective(user);
    }


//    private List<Map<String, Object>> rebuildmenu(List<Map<String, Object>> list) {
//        if(list==null||list.size()==0)return new ArrayList<>();
//        Set<
//        for(List<Map<String, Object> m:list){
//
//        }
//        for (Map<String, Object> map : list) {
//            if(map.get)
//        }
//        return null;
//    }

    public static final String CHILDREN = "children";
    public static final String LEAF = "leaf";
    public static final String CHECKED = "checked";
    public static final String CHECKED_UPPER = "CHECKED";
    public static final String CHECKBOX = "CHECKBOX";
    public static final String NULL = "null";
    public static final String NULLSTRING = "";

    private static void recurseMapTree(List<Map<String, Object>> nodes) {
        Iterator<Map<String, Object>> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> m = iterator.next();
            List<Map<String, Object>> children = (List<Map<String, Object>>) m.get(CHILDREN);
            if (children == null || children.size() == 0) {
                if (!m.get("menu_name").toString().equals("恒丰银行")) {
                    System.out.println("remove " + m.get("menu_name"));
                    iterator.remove();
                }
            } else {
                recurseMapTree(children);
                List<Map<String, Object>> children_ = (List<Map<String, Object>>) m.get(CHILDREN);
                if (children_ == null || children.size() == 0) {
                    if (!m.get("menu_name").toString().equals("恒丰银行")) {
                        System.out.println("remove " + m.get("menu_name"));
                        iterator.remove();
                    }
                }
            }


        }
    }

    public static List<Map<String, Object>> readMapTree(List<Map<String, Object>> nodes) {
        List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
        if (nodes == null || nodes.size() == 0) return rs;
        recurseMapTree(nodes);
        return rs;
    }

    public List<Map<String, Object>> selectNaviTreeMenus(Long portal_id, Long user_id) {
        List<Map<String, Object>> allmenus = menuService.selectMapTreeMenus(portal_id);
        if (allmenus == null || allmenus.size() == 0) return new ArrayList<>();

        System.out.println(UtilJackson.writerWithDefaultPrettyPrinter(allmenus));
        readMapTree(allmenus);
        System.out.println(UtilJackson.writerWithDefaultPrettyPrinter(allmenus));
        Map<String, Map<String, Object>> allmenusmap = new HashMap();
        for (Map<String, Object> map : allmenus) {
            allmenusmap.put(map.get("id").toString(), map);
        }
        List<PermissionBeanDTO> permissions = permissionService.selectPermissionsByUserId(user_id);
        if (permissions == null || permissions.size() == 0) return new ArrayList<>();
        for (PermissionBeanDTO permissionBeanDTO : permissions) {
            if (allmenusmap.containsKey(permissionBeanDTO.getMenuId().toString())) {
                Map<String, Object> menumap = allmenusmap.get(permissionBeanDTO.getMenuId().toString());
                menumap.put("has_operation", true);
            }
        }
//        rebuildmenu(allmenus);


        return null;
        /*List<PermissionBeanDTO> permissions = permissionService.selectPermissionsByUserId(user_id);
        if (permissions == null || permissions.size() == 0) return null;
        List<Long> ids = new ArrayList<>();
        for (PermissionBeanDTO rec : permissions) {
            if (!ids.contains(rec.getMenuId())) {
                ids.add(rec.getMenuId());
            }
        }
        List<Map<String, Object>> menus = menuService.selectMapMenus(portal_id, ids);
        if (menus == null || menus.size() == 0) return null;
        List<Map<String, Object>> list = TreeUtil.buildMapTree(menus, "id", "parent_id");
        return list;*/
    }

    public UserBeanDTO getUser(Long user_id) {
        return null;//userService.selectByPrimaryKey(user_id);
    }

    public PaginateWrapper<List<UserBeanDTO>> getUsers(Pagination pagination) {
        return userService.selectUsers(pagination);
    }

}

//package com.yanll.console;
//
//import com.yanll.auth.console.AuthApplication;
//import com.yanll.auth.service.service.IAuthService;
//import com.yanll.framework.auth.permission.annotation.ConsolePermission;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import java.util.Map;
//import java.util.Set;
//
///**
// * Created by Administrator on 2016/11/18.
// */
//@Ignore
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@SpringBootTest(classes = AuthApplication.class)
//public class AuthTest {
//    @Autowired
//    private WebApplicationContext wac;
//    private MockMvc mockMvc;
//
//    @Autowired
//    private IAuthService authService;
//    @Autowired
//    private PermissionBeanMapper operationBeanMapper;
//
//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }
//
//    @Test
//    public void test() {
//        try {
//            ResultActions rs = null;
//            //rs = mockMvc.perform(post("/console/auth/login").param("username", "admin").param("password", "admin")).andExpect(status().is(200)).andDo(print());
//            //rs = mockMvc.perform(get("/console/auth")).andExpect(status().is(200)).andDo(print());
//            System.out.println(rs);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void InitControlledURLMapping() {
//        System.out.println("****** Init Controlled URLMapping... ******");
//        /*RequestMappingHandlerMapping bean = SpringApplicationContextUtil.getBean(RequestMappingHandlerMapping.class);*/
//        RequestMappingHandlerMapping bean = (RequestMappingHandlerMapping) wac.getBean(RequestMappingHandlerMapping.class);
//        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
//        if (handlerMethods != null) {
//            for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
//                RequestMappingInfo key = entry.getKey();
//                HandlerMethod value = entry.getValue();
//                Class controller = value.getBeanType();
//                if (controller.getName().equals("org.springframework.boot.autoconfigure.web.BasicErrorController"))
//                    continue;
//                ConsolePermission cp = (ConsolePermission) controller.getAnnotation(ConsolePermission.class);
//                ConsolePermission mp = (ConsolePermission) value.getMethodAnnotation(ConsolePermission.class);
//                if (null != cp && !cp.controlled()) continue;
//                if (null != mp && !mp.controlled()) continue;
//                Set<String> patterns = key.getPatternsCondition().getPatterns();
//                Set<RequestMethod> methods = key.getMethodsCondition().getMethods();
//                String url = null;
//                String method = null;
//                for (String s : patterns) {
//                    url = s;
//                    break;
//                }
//                for (RequestMethod m : methods) {
//                    method = m.name();
//                    break;
//                }
//                System.out.println(key.getName() + ":[" + url + ":" + method + "]");
//                PermissionBean ope = new PermissionBean();
//                ope.setUrl(url);
//                ope.setMethod(method);
//                ope.setPermissionName(key.getName());
//                operationBeanMapper.insertSelective(ope);
//            }
//        }
//    }
//}

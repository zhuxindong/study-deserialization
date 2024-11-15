package com.zxd.config;


import com.zxd.filter.CustomLoginFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();

        shiroFilterFactoryBean.setLoginUrl("/toLogin");


        Map<String, String> filterMap = new LinkedHashMap();
        CustomLoginFilter loginFilter = new CustomLoginFilter();
        filters.put("authc",loginFilter);

        filterMap.put("/httpservice/**", "anon");

        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");

        filterMap.put("/user-info/login", "anon");
        filterMap.put("/user-info/registerUser", "anon");
        filterMap.put("/user-info/checkUserNameOrEmail", "anon");

        filterMap.put("/sys-param/getSysParam", "anon");
        filterMap.put("/release-info/getOnlineClientInfo", "anon");
        filterMap.put("/release-info/getOfflineClientInfo", "anon");

        filterMap.put("/netty/**", "anon");
        filterMap.put("/task-info/**", "anon");

        filterMap.put("/miner-info/isActived", "anon");
        filterMap.put("/miner-info/xintiao", "anon");
        filterMap.put("/miner-info/getClientUserMiner", "anon");

        filterMap.put("/actuator/*", "anon");



        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;


    }



    @Bean("securityManager")
    public SecurityManager getDefaultWebSecurityManager(@Qualifier("servletContainerSessionManager")SessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getUserRealm());
        securityManager.setSessionManager(sessionManager);
//        securityManager.setRememberMeManager(null);
        return securityManager;
    }


    @Bean
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

    @Bean("servletContainerSessionManager")
    public ServletContainerSessionManager getServletContainerSessionManager(){
        return new ServletContainerSessionManager();
    }


    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


    /**
     * 开启aop注解支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}

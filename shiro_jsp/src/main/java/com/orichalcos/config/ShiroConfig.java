package com.orichalcos.config;

import com.orichalcos.shiro.realm.CustomRealm;
import com.orichalcos.shiro.cacheManager.RedisCacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author Orichalcos
 */
@Configuration
public class ShiroConfig {
    /**
     * 1.创建ShiroFilter，拦截所有请求
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //注意过滤器配置顺序，不能颠倒
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        filterFactoryBean.setFilterChainDefinitionMap(map);
        map.put("/logout", "logout");
        //配置不会被拦截的链接，顺序判断
        map.put("/login**", "anon");
        map.put("/register**", "anon");
        map.put("/**", "authc");
        //配置shiro默认登录界面地址，前后端分离中登录页面跳转应由前端路由控制，后台仅返回json数据
        filterFactoryBean.setLoginUrl("/login.jsp");
        //登录成功后要跳转的页面
        filterFactoryBean.setSuccessUrl("/index.jsp");
        //未授权页面
        filterFactoryBean.setUnauthorizedUrl("/403.jsp");
        filterFactoryBean.setFilterChainDefinitionMap(map);
        filterFactoryBean.setSecurityManager(securityManager);
        return filterFactoryBean;
    }

    /**
     * 2.创建安全管理器
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("CustomRealm") Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /**
     * 3.创建自定义realm
     */
    @Bean("CustomRealm")
    public Realm getRealm() {
        CustomRealm customRealm = new CustomRealm();
        //修改凭证校验匹配器
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //设置加密算法为MD5
        matcher.setHashAlgorithmName("MD5");
        //设置散列次数
        matcher.setHashIterations(1024);
        customRealm.setCredentialsMatcher(matcher);

        //开启缓存
        customRealm.setCachingEnabled(true);
        customRealm.setAuthenticationCachingEnabled(true);
        customRealm.setAuthenticationCacheName("authenticationCache");
        customRealm.setAuthorizationCachingEnabled(true);
        customRealm.setAuthorizationCacheName("authorizationCache");
        //设置缓存管理器
        customRealm.setCacheManager(new RedisCacheManager());
        return customRealm;
    }
}

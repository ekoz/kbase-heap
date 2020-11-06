/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.config;

import com.google.common.collect.Maps;
import com.ibothub.heap.shiro.cache.RedisCacheManager;
import com.ibothub.heap.shiro.realm.KbsRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Map;

/**
 * 用来整合 Shiro 框架的相关配置类
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/14 23:42
 */
@Configuration
public class ShiroConfig {

    /**
     * 1. 创建 ShiroFilter
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 给 filter 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);


        // 配置系统受限资源

        // 配置系统公共资源
        Map<String, String> filterChainDefinitionMap = Maps.newHashMap();
        // 针对Swagger拦截放行
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/configuration/**", "anon");

        // 对用户注册，登录等地址拦截放行
        filterChainDefinitionMap.put("/user/register", "anon");
        filterChainDefinitionMap.put("/login", "anon");

        // authc 请求该类型的资源需要认证和授权
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        // 默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login");

        return shiroFilterFactoryBean;
    }

    /**
     * 2. 创建安全管理器
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // 给安全管理器设置 realm
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    /**
     * 3. 创建自定义 realm
     */
    @Bean
    @Primary
    public Realm getRealm(){
        KbsRealm kbsRealm = new KbsRealm();
        // 修改凭证校验匹配器
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 设置加密算法
        matcher.setHashAlgorithmName("MD5");
        // 设置 hash 散列
        matcher.setHashIterations(1024);
        kbsRealm.setCredentialsMatcher(matcher);

        // 开启缓存管理
//        kbsRealm.setCacheManager(new EhCacheManager());
        kbsRealm.setCacheManager(new RedisCacheManager());
        kbsRealm.setCachingEnabled(true);
        kbsRealm.setAuthenticationCacheName("authenticationCache");
        kbsRealm.setAuthenticationCachingEnabled(true);
        kbsRealm.setAuthorizationCacheName("authorizationCacheName");
        kbsRealm.setAuthorizationCachingEnabled(true);

        return kbsRealm;
    }

}

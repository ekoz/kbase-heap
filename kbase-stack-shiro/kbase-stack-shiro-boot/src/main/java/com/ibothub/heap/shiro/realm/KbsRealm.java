/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 自定义 realm，将认证/授权数据的读取方法转为数据库实现
 * 加入 md5 + salt + hash散列
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/9 20:06
 */
public class KbsRealm extends AuthorizingRealm {

    /**
     * 负责认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String)token.getPrincipal();
        System.out.println(principal);
        // 根据用户名查询数据库
        if ("ekoz".equals(principal)){
            return new SimpleAuthenticationInfo("ekoz", "ekoz88", this.getName());
        }
        return null;
    }


    /**
     * 负责授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("身份信息：" + primaryPrincipal);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // 添加角色
        authorizationInfo.addRole("admin");
        authorizationInfo.addRole("user");

        // 添加权限
        // user:update:*
        // role:read
        // dept:add:001
        authorizationInfo.addStringPermission("user:*");

        return authorizationInfo;
    }
}

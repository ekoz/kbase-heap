/*
 * powered by https://zhengxinacc.com
 */
package com.ibotstat.heap.shiro.prac02;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义 realm，将认证/授权数据的读取方法转为数据库实现
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
        if ("ekoz".equals(principal)){
            /*
             * 参数说明
             * 1、返回数据库中正确的用户名
             * 2、返回数据库中正确的密码
             * 3、提供当前 realm 的名字
             */
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
        return null;
    }
}

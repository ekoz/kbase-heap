/*
 * powered by https://zhengxinacc.com
 */
package com.ibotstat.heap.shiro.prac03;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * 自定义 realm 就能抛弃 shiro.ini，两个重要的方法是
 * doGetAuthenticationInfo（负责认证）
 * doGetAuthorizationInfo（负责授权）
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/9 20:03
 */
public class TestShiroAuthenticator {

    public static void main(String[] args) {

        // 1.创建安全管理器对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();


        // 2.给安全管理器设置realm
        KbsRealm kbsRealm = new KbsRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(1024);
        kbsRealm.setCredentialsMatcher(credentialsMatcher);
        securityManager.setRealm(kbsRealm);

        // 3.SecurityUtils 全局安全工具类
        SecurityUtils.setSecurityManager(securityManager);

        // 4.关键对象 subject 主体
        Subject subject = SecurityUtils.getSubject();

        // 5.创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("ekoz", "ekoz88");

        // 6.认证

        try {
            subject.login(token);
            System.out.println("认证状态，" + subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("认证失败，用户名不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("认证失败，密码错误");
        }


    }
}

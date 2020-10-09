/*
 * powered by https://zhengxinacc.com
 */
package com.ibotstat.heap.shiro.prac01;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/9/29 21:37
 */
public class TestShiroAuthenticator {

    public static void main(String[] args) {

        // 1.创建安全管理器对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();


        // 2.给安全管理器设置realm
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));

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

/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.realm;

import com.ibothub.heap.shiro.model.entity.User;
import com.ibothub.heap.shiro.service.PermService;
import com.ibothub.heap.shiro.service.RoleService;
import com.ibothub.heap.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

/**
 * 自定义 realm，将认证/授权数据的读取方法转为数据库实现
 * 加入 md5 + salt + hash散列
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/9 20:06
 */
public class KbsRealm extends AuthorizingRealm {

    @Value("${shiro.md5:false}")
    Boolean enableShiroMd5;

    @Resource
    UserService userService;
    @Resource
    RoleService roleService;
    @Resource
    PermService permService;

    /**
     * 负责认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        System.out.println(principal);
        // 根据用户名查询数据库
        User user = userService.findByUsername(principal);
        if (user!=null){
            if (enableShiroMd5) {
                return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
            } else {
                return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), this.getName());
            }
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



        // 根据主身份信息获取 角色 和 权限信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 添加角色
        roleService.getKeysByUsername(primaryPrincipal)
                .forEach(authorizationInfo::addRole);

        // 添加权限
        // user:update:*
        // role:read
        // dept:add:001
        permService.getKeysByUsername(primaryPrincipal)
                .forEach(authorizationInfo::addStringPermission);

        return authorizationInfo;
    }
}

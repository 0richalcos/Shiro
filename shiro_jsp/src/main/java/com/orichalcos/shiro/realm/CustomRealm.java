package com.orichalcos.shiro.realm;

import com.orichalcos.entity.Perms;
import com.orichalcos.entity.Role;
import com.orichalcos.entity.User;
import com.orichalcos.service.ShiroService;
import com.orichalcos.shiro.MyByteSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author Orichalcos
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        String principal = (String) principalCollection.getPrimaryPrincipal();
        //根据主身份信息获取角色信息和权限信息
        List<Role> roles = shiroService.findRolesByUsername(principal);
        if (!CollectionUtils.isEmpty(roles)) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            roles.forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                List<Perms> perms = shiroService.findPermsByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(perms)) {
                    perms.forEach(perm -> {
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取身份信息
        String principal = (String) authenticationToken.getPrincipal();
        //根据身份查询用户信息
        User user = shiroService.findUserByUsername(principal);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), new MyByteSource(user.getSalt()), this.getName());
        }
        return null;
    }
}
package com.zxd.config;



import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserRealm extends AuthorizingRealm {




    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 有些权限只有管理员能访问，这里硬编码给管理员加对应的权限
        Set<String> permSet = new HashSet<String>();



        // 如果是管理员，添加权限
        permSet.add("admin:op:pwd");


        permSet.add("admin:user:qry");
        permSet.add("admin:user:reset");
        permSet.add("admin:user:del");
        permSet.add("admin:user:count");

        permSet.add("admin:notice:add");
        permSet.add("admin:notice:update");
        permSet.add("admin:notice:delete");

        permSet.add("admin:authcode:create");
        permSet.add("admin:authcode:qry");
        permSet.add("admin:authcode:delete");

        permSet.add("admin:miner:qry");

        permSet.add("admin:template:qry");


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permSet);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();


        if (username.equals("zxd")){
            return new SimpleAuthenticationInfo("zxd","zxd",getName());

        }
        throw new AuthenticationException();

    }
}

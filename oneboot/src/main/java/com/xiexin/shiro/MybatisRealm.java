package com.xiexin.shiro;

import com.xiexin.bean.Admin;
import com.xiexin.bean.AdminExample;
import com.xiexin.service.AdminService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//自定义realm
//realm包含认证（登录）和授权2个部分
//
public class MybatisRealm extends AuthorizingRealm {
    @Autowired
    private AdminService adminService;

    @Override  //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override  //认证（登录）
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //AuthenticationToken  这个参数其实就是UsernamePasswordToken（“账号”，“密码”）
        String account = (String) authenticationToken.getPrincipal();//拿到账户名
        //当拿到账户名后，能否拿到数据库中的密码？？怎么拿？？单表的查询
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminAccountEqualTo(account);
        List<Admin> admins = adminService.selectByExample(example);
        Admin dbAdmin = null;
        if (admins != null && admins.size() > 0) {
            dbAdmin = admins.get(0);
            //获取账户名和密码
            String pwd = dbAdmin.getAdminPwd();
            System.out.println("pwd = " + pwd);
            String salt = dbAdmin.getSalt();
            System.out.println("salt = " + salt);
            //进行token认证
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(account, pwd, ByteSource.Util.bytes(salt), this.getName());
            System.out.println("ByteSource.Util.bytes(salt) = " + ByteSource.Util.bytes(salt));
            return simpleAuthenticationInfo;
        }
        return null;
    }
}

package com.xiexin.shirotest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

//shiro的认证
//shiro的三大概念：shiro subject(用户的请求，主体),security Manager(shiro的管理类),realms(数据库)
//realms分为：ini realm，jdbc realm，自定义的realm---常用自定义（mybatis.....）
public class ShiroIni {
    @Test
    public void test01() {
        //1.realms
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        //2.security Manager
        DefaultSecurityManager sm = new DefaultSecurityManager();   //创建管理者
        sm.setRealm(iniRealm);  //用set将数据库中的注入进来
        //以上步骤指的是把realms注入到sm当中，即将他们联系到一起，下面就剩subject
        //subject不是new出来的，因为subject是一个实打实的对象！！原本就有的
        //只需要用一个shiro的类做一个接待就行了
        SecurityUtils.setSecurityManager(sm);  //接管sm
        Subject subject = SecurityUtils.getSubject();
        //就可以使用subject了
        //拟定一个虚拟的账户密码
        String userName = "xiexin";
        String userPwd = "123";
        //在这里。利用shiro把userName和userPwd变为一个token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, userPwd);
        System.out.println("顾客登录的时候把账户名和密码加工后的token = " + usernamePasswordToken);  //前端输入的token
        UsernamePasswordToken dbToken = new UsernamePasswordToken("xiexin", "123");
        System.out.println("数据库中的账户和密码加工后的token = " + dbToken);
        //注意:这个登录的方法是shiro提供的，以后我们自己不写登录！！！

        //UnknownAccountException账户名错误
        //IncorrectCredentialsException密码错误
        try {
            subject.login(usernamePasswordToken);
            System.out.println("登陆成功");
        } catch (UnknownAccountException e) {
            System.out.println("账户名不对");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
            e.printStackTrace();
        }

    }
}

package com.xiexin.shirotest;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;

//shiro的加密和认证测试
public class MyShiro {
//shiro对明文密码123456有加密的功能，让web的密码更加的安全
    //md5加密简单，不可逆，但是可以根据加密后的密码进行反推！！
    //更加的安全！！就需要加盐！！salt
    
    @Test
    public void testCmd5(){
        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println("md5Hash = " + md5Hash);
        //给密码加盐  更安全了
        Md5Hash md5Hash1 = new Md5Hash("123456", "xieshadouxing");
        System.out.println("md5Hash1 = " + md5Hash1);
        //给加盐后的密码进行散列处理
        Md5Hash md5Hash2 = new Md5Hash("123456", "xieshadouxing", 1024); //社工 大数据！！
        System.out.println("md5Hash2 = " + md5Hash2);
    }
}

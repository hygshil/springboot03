package com.xiexin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//springboot项目是为了简化ssm项目存在的
//ssm项目配置比较繁琐，比如，需要配置tomcat，需要跟多个xml配置第三方依赖
//而springboot简化成该内置就内置，多个xml配置改为一个properties.xml文件
//说白了还是ssm框架！！只不过写起来简单了

@SpringBootApplication  //springboot的应用注解，标记本项目是springboot项目，必须有这个注解
@MapperScan("com.xiexin.dao")  //持久包的扫描
public class SmartfoodApplication {
//main方法，项目已启动就会执行该方法
    public static void main(String[] args) {
        //静态调用springApplication应用，参数为本项目的启动类
        System.out.println("springboot项目启动了");
        SpringApplication.run(SmartfoodApplication.class, args);
    }

}

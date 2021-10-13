package com.xiexin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {  ///page/studentList
    @RequestMapping("/studentList")
    public String studentList(){
        return "studentlist";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/regin")
    public String regin(){
        return "regin";
    }
    @RequestMapping("/vuelogin")
    public String vuelogin(){
        return "vuelogin";
    }
    @RequestMapping("/vueregin")
    public String vueregin(){
        return "vueregin";
    }
}

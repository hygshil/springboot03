package com.xiexin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//热部署快捷键ctrl+f9
@Controller
public class IndexController {

    @RequestMapping({"/index","/"})
    public String index(){
        return "index";
    }

}

package cn.johnyu.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
    @RequestMapping("/update")
    public String login(){
        return "login";
    }

    @RequestMapping("/add")
    public String add(){
        return "suc";
    }
}

package com.high.school.users.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginConroller {

    @RequestMapping("login")
    public String login(){
        return "login";
    }
    @RequestMapping("logout")
    public String logout(){
        return "login";
    }
}

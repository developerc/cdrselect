package ru.rtk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @RequestMapping("/")
    public String helloPage(){
        return "index";
    }

    @RequestMapping("/cdr")
    public String cdrPage(){
        return "cdr";
    }

    @RequestMapping("/setaccount")
    public String setaccountPage(){
        return "setaccount";
    }

    @RequestMapping("/setadmin")
    public String setadminPage(){
        return "setadmin";
    }
}

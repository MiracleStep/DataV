package com.mirac.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("main")
public class DatavController {

    @RequestMapping("index")
    public String main(){
        return "index";
    }
}

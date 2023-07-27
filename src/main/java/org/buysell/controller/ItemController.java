package org.buysell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @GetMapping("/")
    public String mainPage(){
        return "mainPage";
    }
}
package com.realestate.courseproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = {"", " ", "/home"})
    public String displayHome(Model model){
        //Model is interface acting as container between UI and backend holding needed values
        model.addAttribute("username", "John Doe");
        return "home.html";
    }
}

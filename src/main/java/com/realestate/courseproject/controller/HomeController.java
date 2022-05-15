package com.realestate.courseproject.controller;

import com.realestate.courseproject.repository.ApartmentRepo;
import com.realestate.courseproject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    ApartmentRepo apartmentRepo;

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = {"", " ", "/home"})
    public String displayHome(Model model){
        //Model is interface acting as container between UI and backend holding needed values
        model.addAttribute("username", "Dear User");
        model.addAttribute("apartCount", apartmentRepo.countApartments());
        model.addAttribute("userCount", userRepo.countUsers());
        return "home.html";
    }
}

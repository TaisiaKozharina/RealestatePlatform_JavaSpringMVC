package com.realestate.courseproject.controller;

import com.realestate.courseproject.model.User;
import com.realestate.courseproject.repository.ApartmentRepo;
import com.realestate.courseproject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    ApartmentRepo apartmentRepo;

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = {"", " ", "/home"})
    public String displayHome(Model model, Authentication authentication, HttpSession session){
        if(authentication != null){
            User user = userRepo.readByEmail(authentication.getName());//authentication.getName() will return email, check LogInAuthenticationProvider method authenticate()
            model.addAttribute("username", user.getUsername());
        }else{
            model.addAttribute("username", "Guest user");
        }

        model.addAttribute("apartCount", apartmentRepo.countApartments());
        model.addAttribute("userCount", userRepo.countUsers());
        return "home.html";
    }
}

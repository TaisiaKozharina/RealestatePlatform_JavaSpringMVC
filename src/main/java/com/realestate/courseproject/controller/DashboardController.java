package com.realestate.courseproject.controller;

import com.realestate.courseproject.model.User;
import com.realestate.courseproject.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Slf4j
@Controller
public class DashboardController {

    @Autowired
    UserRepo userRepo;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session){
        User user = userRepo.readByEmail(authentication.getName()); //authentication.getName() will return email, check LogInAuthenticationProvider method authenticate()
        model.addAttribute("username", user.getUsername());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("currentUser", user);
        return "dashboard.html";
    }
}

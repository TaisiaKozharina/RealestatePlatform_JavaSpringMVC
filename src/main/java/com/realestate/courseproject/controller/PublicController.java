package com.realestate.courseproject.controller;

import com.realestate.courseproject.model.User;
import com.realestate.courseproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/public") //for all paths that have /public before
public class PublicController {

        @Autowired
        UserService userService;

        @RequestMapping(value ="/registration",method = { RequestMethod.GET})
        public String displayRegisterPage(Model model){
                model.addAttribute("user", new User());
                return "registration.html";
        }

        @RequestMapping(value = "/createUser", method = {RequestMethod.POST})
        public String createUser(@Valid @ModelAttribute("user") User user, Errors errors){
                if(errors.hasErrors()){
                        return "registration.html";
                }
                boolean isSaved = userService.createNewUser(user);
                if(isSaved){
                        return "redirect:/login?registration=true";
                } else{
                        return "registration.html";
                }

        }
}

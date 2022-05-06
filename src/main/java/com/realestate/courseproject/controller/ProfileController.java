package com.realestate.courseproject.controller;

import com.realestate.courseproject.model.Profile;
import com.realestate.courseproject.model.User;
import com.realestate.courseproject.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Slf4j
@Controller
public class ProfileController {

    @Autowired
    UserRepo userRepo;

    @RequestMapping("/displayProfile")
    public ModelAndView displayProfile(Model model, HttpSession session){
        User user = (User) session.getAttribute("currentUser");

        Profile profile = new Profile();
        profile.setUsername(user.getUsername());
        profile.setEmail(user.getEmail());
        profile.setMobile_number(user.getMobile_number());

        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }

    @PostMapping(value = "/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors, HttpSession session)
    {
        if(errors.hasErrors()){
            return "profile.html";
        }

        User user = (User) session.getAttribute("currentUser");

        user.setUsername(profile.getUsername());
        user.setEmail(profile.getEmail());
        user.setMobile_number(profile.getMobile_number());
        //user.setConfirmEmail(user.getEmail());
        //user.setConfirmPassword(user.getPassword());

        log.info("User correction details: " + user.toString());
        userRepo.save(user);
        session.setAttribute("currentUser", user);
        return "redirect:/displayProfile";
    }
}

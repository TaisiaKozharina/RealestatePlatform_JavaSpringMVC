package com.realestate.courseproject.controller;

import com.realestate.courseproject.model.Apartment;
import com.realestate.courseproject.model.User;
import com.realestate.courseproject.repository.ApartmentRepo;
import com.realestate.courseproject.repository.UserRepo;
import com.realestate.courseproject.service.ApartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("admin")
public class AdminController {

    @Autowired
    ApartmentRepo apartmentRepo;

    @Autowired
    ApartmentService apartmentService;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/displayApartments")
    public ModelAndView displayApartments(Model model, String comment){


        List<Apartment> apartments = apartmentRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("apartments_secure.html");
        modelAndView.addObject("apartments",apartments);
        modelAndView.addObject("apartment", new Apartment());
        modelAndView.addObject("apartTypes", Apartment.Type.values());
        modelAndView.addObject("comment", comment);
/*        modelAndView.addObject("errors", errors);*/
        return modelAndView;
    }



    @PostMapping("/addNewApartment")
    public String addNewApartment(@Valid @ModelAttribute("apartment") Apartment apartment, Errors errors, HttpSession session, Model model) {
        //ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayApartments");
        if(errors.hasErrors()){
        //modelAndView.addObject("errors", errors);
            return "apartments_secure.html";
        }
        apartmentRepo.save(apartment);
        return "redirect:/admin/displayApartments";
    }


    @RequestMapping("/deleteApartment")
    public ModelAndView deleteApartment(Model model, @RequestParam int id) {
        Optional<Apartment> apartment = apartmentRepo.findById(id);
        for(User users : apartment.get().getUsers()){
            users.getApartments().remove(apartment);
            userRepo.save(users);
        }
        apartmentRepo.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayApartments");
        return modelAndView;
    }

    @GetMapping("/viewUsersWish")
    public ModelAndView viewUsersWish(Model model, @RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("wishlist.html");
        Optional<Apartment> apartment = apartmentRepo.findById(id);
        modelAndView.addObject("apartment", apartment.get());
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
}

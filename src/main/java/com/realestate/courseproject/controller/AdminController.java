package com.realestate.courseproject.controller;

import com.realestate.courseproject.model.Apartment;
import com.realestate.courseproject.model.User;
import com.realestate.courseproject.repository.ApartmentRepo;
import com.realestate.courseproject.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("admin")
public class AdminController {

    @Autowired
    ApartmentRepo apartmentRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/displayApartments")
    public ModelAndView displayApartments(Model model){
        System.out.println(Apartment.Type.values());
        List<Apartment> apartments = apartmentRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("apartments_secure.html");
        modelAndView.addObject("apartments",apartments);
        modelAndView.addObject("apartment", new Apartment());
        modelAndView.addObject("apartTypes", Apartment.Type.values());
        return modelAndView;
    }



    @PostMapping("/addNewApartment")
    public ModelAndView addNewApartment(Model model, @ModelAttribute("apartment") Apartment apartment) {
        ModelAndView modelAndView = new ModelAndView();
        apartmentRepo.save(apartment);
        modelAndView.setViewName("redirect:/admin/displayApartments");
        return modelAndView;
    }

    @RequestMapping("/deleteApartment")
    public ModelAndView deleteApartment(Model model, @RequestParam int id) {
        Optional<Apartment> apartment = apartmentRepo.findById(id);
        for(User users : apartment.get().getUsers()){
            users.setApartments(null);
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

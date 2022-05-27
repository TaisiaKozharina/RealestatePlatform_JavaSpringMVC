package com.realestate.courseproject.controller;

import com.realestate.courseproject.dto.ApartmentDTO;
import com.realestate.courseproject.model.Apartment;
import com.realestate.courseproject.model.User;
import com.realestate.courseproject.repository.ApartmentRepo;
import com.realestate.courseproject.repository.UserRepo;
import com.realestate.courseproject.service.ApartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
public class ApartmentController {

    @Autowired
    private ApartmentRepo apartmentRepo;

    @Autowired
    private UserRepo userRepo;

    ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    /*@GetMapping("/gallery/{display}") : in method params add @PathVariable String display, */
    @GetMapping("/gallery")
    public ModelAndView displayApartments (Model model, ApartmentDTO apartmentDTO, Authentication authentication) {

        List<Apartment> apartments = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView("gallery.html");
        if(authentication != null) {
            User user = userRepo.readByEmail(authentication.getName());
            modelAndView.addObject("user", user);
            List<Integer> userApCodeList = new ArrayList<>();
            for(Apartment ap : user.getApartments()){
                userApCodeList.add(ap.getCode());
            }
            modelAndView.addObject("userApCodeList", userApCodeList);
        }

        apartments = apartmentService.findByFilters(apartmentDTO);
           // apartments = apartmentRepo.findAll(Sort.by("city").descending());
        //List<Apartment> apartments = apartmentRepo.findByOrderByCityDesc();

        modelAndView.addObject("apartDTO", apartmentDTO);
        modelAndView.addObject("apartments", apartments);
        modelAndView.addObject("apartTypes", Apartment.Type.values());

        return modelAndView;
    }

    @RequestMapping("/addApartToWishlist")
    public ModelAndView addApartmentToWishList(Model model, @RequestParam int id, Authentication authentication, HttpSession session) {

        if(authentication != null){
            User user = userRepo.readByEmail(authentication.getName());
            Apartment apartmentToAdd = apartmentRepo.getByCode(id);
            user.getApartments().add(apartmentToAdd);
            apartmentToAdd.getUsers().add(user);
            userRepo.save(user);
            apartmentRepo.save(apartmentToAdd);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/gallery");
        return modelAndView;
    }

    @RequestMapping("/removeApartFromWishlist")
    public ModelAndView removeApartmentFromWishList(Model model, @RequestParam int id, Authentication authentication, HttpSession session) {

        if(authentication != null){
            User user = userRepo.readByEmail(authentication.getName());
            Apartment apartmentToRemove = apartmentRepo.getByCode(id);
            user.getApartments().remove(apartmentToRemove);
            apartmentToRemove.getUsers().remove(user);
            userRepo.save(user);
            apartmentRepo.save(apartmentToRemove);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/gallery");
        return modelAndView;
    }

}

package com.realestate.courseproject.controller;

import com.realestate.courseproject.model.Apartment;
import com.realestate.courseproject.repository.ApartmentRepo;
import com.realestate.courseproject.service.ApartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    /*@GetMapping("/gallery/{display}") : in method params add @PathVariable String display, */
    @GetMapping("/gallery")
    public ModelAndView displayApartments(Model model,
                                          @Param("filterCity") String city,
                                          @Param("filterType") Apartment.Type type,
                                          @Param("filterRooms") Integer rooms) {

        /*if(display != null && display.equals("all")){
            model.addAttribute("flat", true);
            model.addAttribute("house", true);
            model.addAttribute("mansion", true);
        }
        else if (display != null && display.equals("flat")){
            model.addAttribute("flat", true);
        }
        else if (display != null && display.equals("house")){
            model.addAttribute("house", true);
        }
        else if (display != null && display.equals("mansion")){
            model.addAttribute("mansion", true);
        }


        Iterable<Apartment> apartments = apartmentRepo.findAll();
        //Can't create a stream of Iterable, hence calling StreamSupport to create a list from iterable and then execute lambda logic with streams
        List<Apartment> apartList = StreamSupport.stream(apartments.spliterator(), false).collect(Collectors.toList());
        Apartment.Type[] types = Apartment.Type.values();
        for (Apartment.Type type : types) {
            model.addAttribute(type.toString(),
                    (apartList.stream().filter(apartment -> apartment.getType().equals(type)).collect(Collectors.toList())));
        }*/

        //List<Apartment> apartments = apartmentRepo.findByOrderByCityDesc();
        List<Apartment> apartments = apartmentRepo.findAll(Sort.by("city").descending());
        ModelAndView modelAndView = new ModelAndView("gallery.html");
        modelAndView.addObject("apartments", apartments);
        return modelAndView;
    }


}

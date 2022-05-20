package com.realestate.courseproject.controller;

import com.realestate.courseproject.dto.ApartmentDTO;
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

    ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    /*@GetMapping("/gallery/{display}") : in method params add @PathVariable String display, */
    @GetMapping("/gallery")
    public ModelAndView displayApartments (Model model, ApartmentDTO apartmentDTO) {

        List<Apartment> apartments = new ArrayList<>();

        /*if (apartmentDTO != null){
            apartments = apartmentService.findByFilters(apartmentDTO);
        }*/

        apartments = apartmentService.findByFilters(apartmentDTO);
           // apartments = apartmentRepo.findAll(Sort.by("city").descending());
        //List<Apartment> apartments = apartmentRepo.findByOrderByCityDesc();
        ModelAndView modelAndView = new ModelAndView("gallery.html");
        modelAndView.addObject("apartDTO", apartmentDTO);
        modelAndView.addObject("apartments", apartments);
        modelAndView.addObject("apartTypes", Apartment.Type.values());

        return modelAndView;
    }


}

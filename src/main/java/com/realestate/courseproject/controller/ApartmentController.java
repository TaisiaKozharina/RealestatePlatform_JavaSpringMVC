package com.realestate.courseproject.controller;

import com.realestate.courseproject.model.Apartment;
import com.realestate.courseproject.repository.ApartmentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping("/gallery/{display}")
    public String displayApartments(@PathVariable String display, Model model) {

        if(display != null && display.equals("all")){
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

        //Here it is ok to skip the Service layer, because not much business logic is implemented, just the method invocation
        Iterable<Apartment> apartments = apartmentRepo.findAll();
        //Cant create a stream of Iterable, hence calling StreamSupport to create a list from iterable and then execute lambda logic with streams
        List<Apartment> apartList = StreamSupport.stream(apartments.spliterator(), false).collect(Collectors.toList());
        Apartment.Type[] types = Apartment.Type.values();
        for (Apartment.Type type : types) {
            model.addAttribute(type.toString(),
                    (apartList.stream().filter(apartment -> apartment.getType().equals(type)).collect(Collectors.toList())));
        }
        return "gallery.html";
    }


}

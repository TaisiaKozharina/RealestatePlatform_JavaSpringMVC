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

@Slf4j
@Controller
public class ApartmentController {

    @Autowired
    private ApartmentRepo apartmentRepo;

    /*@GetMapping("/gallery")
    public String displayApartments(@RequestParam(required = false) boolean flat,
                                    @RequestParam(required = false) boolean house,
                                    @RequestParam(required = false) boolean mansion,
                                    Model model) {
        model.addAttribute("flat", flat);
        model.addAttribute("house", house);
        model.addAttribute("mansion", mansion);


        List<Apartment> apartments = Arrays.asList(
                new Apartment(Apartment.Type.FLAT, "Deglava 75", 32000.00, 45),
                new Apartment(Apartment.Type.HOUSE, "Aspazijas 44", 111000.00, 90.5),
                new Apartment(Apartment.Type.FLAT, "Marijas 26b", 56560.00, 56),
                new Apartment(Apartment.Type.FLAT, "Dombrovska 201", 41200.00, 35.2),
                new Apartment(Apartment.Type.HOUSE, "Bikernieku 111", 55000.50, 40),
                new Apartment(Apartment.Type.MANSION, "Bukaisu 4", 200000.00, 98),
                new Apartment(Apartment.Type.MANSION, "Krasta 80", 152000.00, 100),
                new Apartment(Apartment.Type.FLAT, "Mucinieku 11a", 61600.00, 33.5)
        );
        Apartment.Type[] types = Apartment.Type.values();
        for (Apartment.Type type : types) {
            model.addAttribute(type.toString(),
                    (apartments.stream().filter(apartment -> apartment.getType().equals(type)).collect(Collectors.toList())));
        }
        return "gallery.html";
    }*/

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
        List<Apartment> apartments = apartmentRepo.findAllApartments();
        Apartment.Type[] types = Apartment.Type.values();
        for (Apartment.Type type : types) {
            model.addAttribute(type.toString(),
                    (apartments.stream().filter(apartment -> apartment.getType().equals(type)).collect(Collectors.toList())));
        }
        return "gallery.html";
    }


}

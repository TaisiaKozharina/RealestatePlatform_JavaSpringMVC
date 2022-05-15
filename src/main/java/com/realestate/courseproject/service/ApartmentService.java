package com.realestate.courseproject.service;

import com.realestate.courseproject.model.Apartment;
import com.realestate.courseproject.repository.ApartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ApartmentService {

    @Autowired
    ApartmentRepo apartmentRepo;

/*
    static Specification<Apartment> cityLike(String filterCity) {
        return (apartment, cq, cb) -> cb.like(apartment.get("city"), filterCity);
    }

    static Specification<Apartment> addressLike(String filterAddress) {
        return (apartment, cq, cb) -> cb.like(apartment.get("address"), filterAddress);
    }

    static Specification<Apartment> filterBetween(float value, float upperBound, float lowerBound) {
        return (apartment, cq, cb) -> cb.between(apartment.get("price"), upperBound, lowerBound);
    }

    public List<Apartment> findByFilters(String filterCity, String filterAddress){
        List<Apartment> matchingApartments = apartmentRepo.findAll(where(cityLike(filterCity)).and(addressLike(filterAddress)));
        return matchingApartments;
    }*/


}

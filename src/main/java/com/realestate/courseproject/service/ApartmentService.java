package com.realestate.courseproject.service;

import com.realestate.courseproject.dto.ApartmentDTO;
import com.realestate.courseproject.model.Apartment;
import com.realestate.courseproject.repository.ApartmentRepo;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Objects;

@Service
public class ApartmentService {

    @Autowired
    ApartmentRepo apartmentRepo;

    @Autowired
    EntityManager em;

//    CriteriaBuilder builder = em.getCriteriaBuilder();

    static Specification<Apartment> cityLike(String filterCity) {
        return (apartment, cq, cb) -> cb.like(apartment.get("city"), filterCity);
    }

    static Specification<Apartment> addressLike(String filterAddress) {
        return (apartment, cq, cb) -> cb.like(apartment.get("address"), "%" + filterAddress + "%");
    }

    static Specification<Apartment> filterBetween(Double upperBound, Double lowerBound) {
        return (apartment, cq, cb) -> cb.between(apartment.get("price"), lowerBound, upperBound);
    }

    public List<Apartment> findByFilters(ApartmentDTO apartmentDTO) {
//        Specification<Apartment> s = cityLike(apartmentDTO.city)
//                .and(addressLike(apartmentDTO.address))
//                .and(filterBetween(apartmentDTO.upperBound, apartmentDTO.lowerBound));
        Specification<Apartment> s = filterBetween(apartmentDTO._getUpperBound(), apartmentDTO._getLowerBound());
        if (!StringHelper.isEmpty(apartmentDTO.address)) {
            s = s.and(addressLike(apartmentDTO.address));
        }
        if (!StringHelper.isEmpty(apartmentDTO.city)) {
            s = s.and(cityLike(apartmentDTO.city));
        }
        List<Apartment> matchingApartments = apartmentRepo.findAll(s);
        return matchingApartments;
    }

}

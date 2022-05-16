package com.realestate.courseproject.dto;

import com.realestate.courseproject.model.Apartment;
import lombok.Data;

@Data
public class ApartmentDTO {
    //all filter params

    public String city;
    public String address;
    public Integer rooms;
    public Double lowerBound;
    public Double upperBound;
    public Apartment.Type type;

    public Double _getLowerBound() {
        if(lowerBound == null) {
            return Double.MIN_VALUE;
        }
        return lowerBound;
    }

    public Double _getUpperBound() {
        if(upperBound == null) {
            return Double.MAX_VALUE;
        }
        return upperBound;
    }
}

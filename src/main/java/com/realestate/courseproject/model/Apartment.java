package com.realestate.courseproject.model;

import lombok.Data;

@Data
public class Apartment extends BaseEntity{

    private int code;
    private Type type;
    private String city;
    private String address;
    private double price;
    private double size;
    private int rooms;
    private String description;
    private String photo_url; //URL of image
    //private final boolean isActive;


    public enum Type{
        FLAT, HOUSE, MANSION
    }

}

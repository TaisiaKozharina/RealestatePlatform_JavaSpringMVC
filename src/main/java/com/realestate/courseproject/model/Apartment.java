package com.realestate.courseproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "apartments")
public class Apartment extends BaseEntity{

    @Id
    private int code;

    @Enumerated(EnumType.STRING) //Spring JPA will have data on enum options and convert it into varchar for DB
    private Type type;
    public enum Type{
        FLAT, HOUSE, MANSION
    }

    private String city;

    private String address;

    private double price;

    private double size;

    private int rooms;

    private String description;

    private String photo_url; //URL of image

    //private final boolean isActive;




}

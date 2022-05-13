package com.realestate.courseproject.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "apartments")
public class Apartment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int code;

    @Enumerated(EnumType.STRING) //Spring JPA will have data on enum options and convert it into varchar for DB
    private Type type;
    public enum Type{
        FLAT ("Flat"),
        HOUSE("House"),
        MANSION ("Mansion");

        private final String typeName;

        Type(String typeName){
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    private String city;

    private String address;

    private double price;

    private double size;

    private int rooms;

    private String description;

    private String photo_url; //URL of image

    //@ManyToMany(mappedBy = "apartments", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    //private Set<User> users = new HashSet<>();

    //private final boolean isActive;




}

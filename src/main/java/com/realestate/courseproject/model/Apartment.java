package com.realestate.courseproject.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank(message="City field must not be blank")
    private String city;

    @NotBlank(message="Address field must not be blank")
    private String address;

    @Min(value = 1, message="Price field must not be zero")
    private double price;

    @Min(value = 1, message="Size field must not be zero")
    private double size;

    @Min(value = 1, message="Rooms field must not be zero")
    private int rooms;

    @NotBlank(message="Description is mandatory")
    @Size(min=20, message="Description must be at least 20 characters long")
    private String description;

    private String photo_url; //URL of image

    @ManyToMany(mappedBy = "apartments", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST) //Eager to enable joined queries. Persist because if deleting apartment, do not delete users also
    private Set<User> users = new HashSet<>();

    //private final boolean isActive;

}

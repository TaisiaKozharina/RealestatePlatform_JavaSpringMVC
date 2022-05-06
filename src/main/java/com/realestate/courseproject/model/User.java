package com.realestate.courseproject.model;

import com.realestate.courseproject.annotations.FieldMatcher;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Data
@Getter
@Setter
@Entity
@Table(name="app_user")
//@FieldMatcher.List({
//        @FieldMatcher(
//                field = "password",
//                fieldMatch = "confirmPassword",
//                message = "Passwords do not match!"
//        ),
//        @FieldMatcher(
//                field = "email",
//                fieldMatch = "confirmEmail",
//                message = "Emails do not match!"
//        )
//})
public class User{


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "userid")
    private int userID;

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String username;

    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @Column(name = "mobile_number")
    private String mobile_number;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;

/*    @NotBlank(message="Confirm Email must not be blank")
    @Email(message = "Please provide a valid confirm email address" )
    @Transient //Not considering this field in DB - will not be stored
    private transient String confirmEmail;*/

    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    @Column(name = "pwd")
    private String password;

/*    @NotBlank(message="Confirm Password must not be blank")
    @Size(min=5, message="Confirm Password must be at least 5 characters long")
    @Transient
    private String confirmPassword;*/

    //Eager because there might be joined queries, cascade persist because whenever person is saved, the roles should be also saved (not null FK), target is optional
    //role_id actual DB column, referColName - from class
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private Role role;


    /*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "wishlist",
            joinColumns = {@JoinColumn(name = "userid", referencedColumnName = "userID")},
            inverseJoinColumns =  {@JoinColumn(name = "code", referencedColumnName = "code")})
    private Set<Apartment> apartments = new HashSet<>();*/

}

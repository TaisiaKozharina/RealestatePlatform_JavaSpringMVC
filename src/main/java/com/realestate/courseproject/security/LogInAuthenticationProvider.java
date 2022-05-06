package com.realestate.courseproject.security;

import com.realestate.courseproject.model.Role;
import com.realestate.courseproject.model.User;
import com.realestate.courseproject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogInAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //custom authentication logic
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepo.readByEmail(email); //roles will also be populated because of EAGER fetch

        if(user != null && user.getUserID()>0 && passwordEncoder.matches(password, user.getPassword()))
        {
            //password will be erased by Spring Security after successful authentication. Passing null to protect sensitive info (optional)
            return new UsernamePasswordAuthenticationToken(email, null, getGrantedAuthorities(user.getRole()));
        }
        else
        {
            throw new BadCredentialsException("Invalid credentials. Try again.");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Role role){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())); //appending prefix because Spring Security maintains roles with this prefix
        //SimpleGrantedAuthority is an implementation of GrantedAuthority (interface), indirect object creation
        //List, because user may have multiple roles (not the CP case)
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //checking if given authentication object is of class UsernamePassword, if same - will try to execute authenticate method
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

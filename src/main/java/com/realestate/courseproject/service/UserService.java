package com.realestate.courseproject.service;

import com.realestate.courseproject.constants.GlobalConstants;
import com.realestate.courseproject.model.Role;
import com.realestate.courseproject.model.User;
import com.realestate.courseproject.repository.RoleRepo;
import com.realestate.courseproject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    public boolean createNewUser(User user){
        boolean isSaved = false;
        Role role = roleRepo.getByRoleName(GlobalConstants.USER_ROLE);
        user.setRole(role);
        user = userRepo.save(user);
        if (null != user &&user.getUserID() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }
}

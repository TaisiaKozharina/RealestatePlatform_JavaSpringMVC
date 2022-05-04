package com.realestate.courseproject.repository;

import com.realestate.courseproject.model.Role;
import com.realestate.courseproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}

package com.realestate.courseproject.repository;

import com.realestate.courseproject.model.Role;
import com.realestate.courseproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    //derived query
    User readByUsername(String username);
    User readByEmail(String email);

    @Query("SELECT count(*) FROM User")
    Integer countUsers();
}

package com.realestate.courseproject.repository;

import com.realestate.courseproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role getByRoleName(String roleName);
}

package com.realestate.courseproject.repository;

import com.realestate.courseproject.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepo extends JpaRepository<Apartment, Integer> {
    //The JpaRepository extends PagingAndSortingRepository, which we need for sorting and filtering

    List<Apartment> findByOrderByCityDesc();
    List<Apartment> findByOrderByCity(); //by default ascending, but can mention "Asc" in the end explicitly.

    @Query("SELECT count(*) FROM Apartment")
    Integer countApartments();
}

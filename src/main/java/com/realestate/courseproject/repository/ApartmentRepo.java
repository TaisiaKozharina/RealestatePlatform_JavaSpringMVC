package com.realestate.courseproject.repository;

import com.realestate.courseproject.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApartmentRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ApartmentRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Apartment> findAllApartments() {
        //wherever field and column names are matching you don't need to write custom rowMapper. Using BeanPropertyRowMapper
        //Able to put APARTMENTS in capital letters because MS SQL Server managements studio is not case sensitive
        String sqlQuery = "SELECT * FROM APARTMENTS";
        var rowMapper = BeanPropertyRowMapper.newInstance(Apartment.class);
        return jdbcTemplate.query(sqlQuery, rowMapper);

    }

}

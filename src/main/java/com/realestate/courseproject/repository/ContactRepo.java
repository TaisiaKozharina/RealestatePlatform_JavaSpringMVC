package com.realestate.courseproject.repository;


import com.realestate.courseproject.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContactRepo extends PagingAndSortingRepository<Contact, Integer> {

    //Fetches record according to the status. Return List as there are probably many messages of one type
    List<Contact> findByStatus(String status);

    @Query("SELECT c FROM Contact c WHERE c.status = :status") //param in method is status, but can use ?1 isnstead of :status
    Page<Contact> findByStatus(String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactID = ?2")
    int updateStatusByID(String status, int id);

}

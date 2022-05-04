package com.realestate.courseproject.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    //Will need it in all model classes, so separated them in another own class
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}

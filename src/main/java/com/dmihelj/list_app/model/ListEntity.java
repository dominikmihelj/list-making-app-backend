package com.dmihelj.list_app.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListEntity {
    private Integer id;
    private String name;
    private Integer boardId; // Foreign key to boards table
    private LocalDateTime createdAt;

}

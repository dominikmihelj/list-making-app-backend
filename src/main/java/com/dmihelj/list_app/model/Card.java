package com.dmihelj.list_app.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
    private Integer id;
    private String name;
    private String description;
    private Integer listId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

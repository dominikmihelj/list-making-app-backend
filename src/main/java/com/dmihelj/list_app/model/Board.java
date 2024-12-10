package com.dmihelj.list_app.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private Integer id;
    private String name;
    private LocalDateTime createdAt;
    private List<ListEntity> lists;
}

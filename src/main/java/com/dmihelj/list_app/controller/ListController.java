package com.dmihelj.list_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.dmihelj.list_app.model.ListEntity;
import com.dmihelj.list_app.service.ListService;


@RestController
@RequestMapping("/api/boards/{boardId}/lists")
public class ListController {

    @Autowired
    private ListService listService;

    // Create a new list for a board
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<ListEntity> createList(@PathVariable Integer boardId, @RequestBody ListEntity listEntity) {
        listEntity.setBoardId(boardId); // Set the board ID in the list
        listService.createList(listEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(listEntity);
    }

    // Get all lists for a board
    @GetMapping
    public List<ListEntity> getListsByBoardId(@PathVariable Integer boardId) {
        return listService.getListsByBoardId(boardId);
    }

    // Get list by ID
    @GetMapping("/{id}")
    public ResponseEntity<ListEntity> getListById(@PathVariable Integer boardId, @PathVariable Integer id) {
        ListEntity listEntity = listService.getListById(id);
        if (listEntity != null && listEntity.getBoardId().equals(boardId)) {
            return ResponseEntity.ok(listEntity);
        }
        return ResponseEntity.notFound().build();
    }

    // Update list name
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<ListEntity> updateList(@PathVariable Integer boardId, @PathVariable Integer id, @RequestBody ListEntity listEntity) {
        listEntity.setBoardId(boardId); // Set the board ID from the path to the list entity
        int updatedRows = listService.updateList(id, listEntity);
        if (updatedRows > 0) {
            return ResponseEntity.ok(listEntity);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a list
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable Integer boardId, @PathVariable Integer id) {
        int deletedRows = listService.deleteList(id);
        if (deletedRows > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


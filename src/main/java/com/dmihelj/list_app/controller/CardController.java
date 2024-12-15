package com.dmihelj.list_app.controller;

import com.dmihelj.list_app.model.Card;
import com.dmihelj.list_app.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardId}/lists/{listId}/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    //@PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Card> createCard(@PathVariable Integer boardId, @PathVariable Integer listId, @RequestBody Card card) {
        card.setListId(listId); // Associate the card with the list
        cardService.createCard(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(card);
    }

    @GetMapping
    public List<Card> getCardsByListId(@PathVariable Integer boardId, @PathVariable Integer listId) {
        return cardService.getCardsByListId(listId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Integer boardId, @PathVariable Integer listId, @PathVariable Integer id) {
        Card card = cardService.getCardById(id);
        if (card != null && card.getListId().equals(listId)) {
            return ResponseEntity.ok(card);
        }
        return ResponseEntity.notFound().build();
    }

    //@PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Integer boardId, @PathVariable Integer listId, @PathVariable Integer id, @RequestBody Card card) {
        card.setId(id);
        card.setListId(listId);
        int updatedRows = cardService.updateCard(card);
        if (updatedRows > 0) {
            return ResponseEntity.ok(card);
        }
        return ResponseEntity.notFound().build();
    }

    //@PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Integer boardId, @PathVariable Integer listId, @PathVariable Integer id) {
        int deletedRows = cardService.deleteCard(id);
        if (deletedRows > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

package com.dmihelj.list_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.dmihelj.list_app.model.Board;
import com.dmihelj.list_app.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        Board board = boardService.getBoardById(id);
        if (board != null) {
            return ResponseEntity.ok(board);
        }
        return ResponseEntity.notFound().build();
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        int result = boardService.createBoard(board);
            return ResponseEntity.status(HttpStatus.CREATED).body(board);
    }

    //@PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable Long id, @RequestBody Board board) {
        int result = boardService.updateBoard(id, board.getName());
        if (result > 0) {
            return ResponseEntity.ok("Board updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found or update failed");
    }

    //@PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        int result = boardService.deleteBoard(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

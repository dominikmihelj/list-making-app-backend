package com.dmihelj.list_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;  // Import this for @PreAuthorize

import com.dmihelj.list_app.model.Board;
import com.dmihelj.list_app.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/api/boards")  // Base URL for all board-related endpoints
public class BoardController {

    private final BoardService boardService;

    @Autowired  // Constructor-based dependency injection
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // Allow all users (authenticated or not) to view the boards
    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();  // Returns all boards
    }

    // Allow all users (authenticated or not) to view a specific board
    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);  // Returns a specific board by ID
    }

    // Only authenticated users with 'USER' role can create a board
    @PreAuthorize("hasRole('USER')")
    @PostMapping  // Handles POST requests to /api/boards
    public String createBoard(@RequestBody Board board) {
        int result = boardService.createBoard(board);  // Creates a new board
        if (result > 0) {
            return "Board created successfully";
        } else {
            return "Board creation failed";
        }
    }

    // Only authenticated users with 'USER' role can update a board
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")  // Handles PUT requests to /api/boards/{id}
    public String updateBoard(@PathVariable Long id, @RequestBody Board board) {
        int result = boardService.updateBoard(id, board.getName());  // Updates the board
        if (result > 0) {
            return "Board updated successfully";
        } else {
            return "Board update failed";
        }
    }

    // Only authenticated users with 'USER' role can delete a board
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")  // Handles DELETE requests to /api/boards/{id}
    public String deleteBoard(@PathVariable Long id) {
        int result = boardService.deleteBoard(id);  // Deletes the board
        if (result > 0) {
            return "Board deleted successfully";
        } else {
            return "Board deletion failed";
        }
    }
}

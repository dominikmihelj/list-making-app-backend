package com.dmihelj.list_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dmihelj.list_app.dao.BoardDao;
import com.dmihelj.list_app.model.Board;

import java.util.List;

@Service
public class BoardService {

    private final BoardDao boardDao;

    @Autowired  // Constructor-based dependency injection
    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public List<Board> getAllBoards() {
        return boardDao.getAllBoards();  // Calls the DAO layer to fetch boards
    }

    public Board getBoardById(Long id) {
        return boardDao.getBoardById(id);  // Calls the DAO layer to fetch a board by ID
    }

    public int createBoard(Board board) {
        return boardDao.createBoard(board);  // Calls the DAO layer to create a new board
    }

    public int updateBoard(Long id, String name) {
        return boardDao.updateBoard(id, name);  // Calls the DAO layer to update the board
    }

    public int deleteBoard(Long id) {
        return boardDao.deleteBoard(id);  // Calls the DAO layer to delete a board
    }
}

package com.dmihelj.list_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dmihelj.list_app.dao.BoardDao;
import com.dmihelj.list_app.model.Board;

import java.util.List;

@Service
public class BoardService {

    private final BoardDao boardDao;

    @Autowired
    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public List<Board> getAllBoards() {
        return boardDao.getAllBoards();
    }

    public Board getBoardById(Long id) {
        return boardDao.getBoardById(id);
    }

    public int createBoard(Board board) {
        return boardDao.createBoard(board);
    }

    public int updateBoard(Long id, String name) {
        return boardDao.updateBoard(id, name);
    }

    public int deleteBoard(Long id) {
        return boardDao.deleteBoard(id);
    }
}

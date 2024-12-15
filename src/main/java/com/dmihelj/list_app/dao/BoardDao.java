package com.dmihelj.list_app.dao;

import com.dmihelj.list_app.model.Board;
import com.dmihelj.list_app.model.ListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BoardDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createBoard(Board board) {
        String sql = "INSERT INTO boards (name, created_at) VALUES (?, ?)";
        return jdbcTemplate.update(sql, board.getName(), LocalDateTime.now());
    }

    public Board getBoardById(Long id) {
        String sql = "SELECT * FROM boards WHERE id = ?";
        Board board = jdbcTemplate.queryForObject(sql, this::mapRowToBoard, id);

        String listSql = "SELECT id, name, board_id, created_at FROM lists WHERE board_id = ?";
        List<ListEntity> lists = jdbcTemplate.query(listSql, (resultSet, rowNum) -> {
            ListEntity listEntity = new ListEntity();
            listEntity.setId(resultSet.getInt("id"));
            listEntity.setName(resultSet.getString("name"));
            listEntity.setBoardId(resultSet.getInt("board_id"));
            listEntity.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime()); // Make sure createdAt is set
            return listEntity;
        }, id);

        board.setLists(lists);

        return board;
    }



    public List<Board> getAllBoards() {
        String sql = "SELECT * FROM boards";
        return jdbcTemplate.query(sql, this::mapRowToBoard);
    }

    public int updateBoard(Long id, String name) {
        String sql = "UPDATE boards SET name = ?, created_at = ? WHERE id = ?";
        return jdbcTemplate.update(sql, name, LocalDateTime.now(), id);
    }

    public int deleteBoard(Long id) {
        String sql = "DELETE FROM boards WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private Board mapRowToBoard(ResultSet resultSet, int rowNum) throws SQLException {
        Board board = new Board();
        board.setId(resultSet.getInt("id"));
        board.setName(resultSet.getString("name"));
        board.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        return board;
    }

    private ListEntity mapRowToListEntity(ResultSet resultSet, int rowNum) throws SQLException {
        ListEntity listEntity = new ListEntity();
        listEntity.setId(resultSet.getInt("id"));
        listEntity.setName(resultSet.getString("name"));
        listEntity.setBoardId(resultSet.getInt("board_id"));
        listEntity.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());  // Make sure createdAt is mapped
        return listEntity;
    }

}

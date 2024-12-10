package com.dmihelj.list_app.dao;


import com.dmihelj.list_app.model.ListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ListDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Helper method to map a ResultSet to a List object
    private ListEntity mapRowToList(ResultSet resultSet, int rowNum) throws SQLException {
        ListEntity list = new ListEntity();
        list.setId(resultSet.getInt("id"));
        list.setName(resultSet.getString("name"));
        list.setBoardId(resultSet.getInt("board_id"));
        return list;
    }

    // Example method to get a List by ID
    public ListEntity getListById(Integer id) {
        String sql = "SELECT * FROM lists WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToList, id);
    }

    // Example method to get all Lists for a board
    public List<ListEntity> getListsByBoardId(Integer boardId) {
        String sql = "SELECT * FROM lists WHERE board_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToList, boardId);
    }

    // Example method to create a List
    public int createList(ListEntity list) {
        String sql = "INSERT INTO lists (name, board_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, list.getName(), list.getBoardId());
    }

    // Example method to update a List
    public int updateList(ListEntity list) {
        String sql = "UPDATE lists SET name = ?, board_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, list.getName(), list.getBoardId(), list.getId());
    }

    // Example method to delete a List
    public int deleteList(Integer id) {
        String sql = "DELETE FROM lists WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}


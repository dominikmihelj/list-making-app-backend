package com.dmihelj.list_app.dao;

import com.dmihelj.list_app.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CardDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Card mapRowToCard(ResultSet resultSet, int rowNum) throws SQLException {
        Card card = new Card();
        card.setId(resultSet.getInt("id"));
        card.setName(resultSet.getString("name"));
        card.setDescription(resultSet.getString("description"));
        card.setListId(resultSet.getInt("list_id"));
        card.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        return card;
    }

    public Card getCardById(Integer id) {
        String sql = "SELECT * FROM cards WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToCard, id);
    }

    public List<Card> getCardsByListId(Integer listId) {
        String sql = "SELECT * FROM cards WHERE list_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToCard, listId);
    }

    public int createCard(Card card) {
        String sql = "INSERT INTO cards (name, list_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, card.getName(), card.getListId());
    }

    public int updateCard(Card card) {
        String sql = "UPDATE cards SET description = ? WHERE id = ?";
        return jdbcTemplate.update(sql, card.getDescription(), card.getId());
    }

    public int deleteCard(Integer id) {
        String sql = "DELETE FROM cards WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}


package com.dmihelj.list_app.dao;

import com.dmihelj.list_app.model.Card;
import com.dmihelj.list_app.model.ListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ListDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ListEntity mapRowToList(ResultSet resultSet, int rowNum) throws SQLException {
        ListEntity list = new ListEntity();
        list.setId(resultSet.getInt("id"));
        list.setName(resultSet.getString("name"));
        list.setBoardId(resultSet.getInt("board_id"));
        list.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());  // map created_at
        return list;
    }

    private List<Card> getCardsForList(Integer listId) {
        String sql = "SELECT * FROM cards WHERE list_id = ?";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Card card = new Card();
            card.setId(resultSet.getInt("id"));
            card.setName(resultSet.getString("name"));
            card.setDescription(resultSet.getString("description"));
            card.setListId(resultSet.getInt("list_id"));
            card.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
            return card;
        }, listId);
    }

    public ListEntity getListById(Integer id) {
        String sql = "SELECT * FROM lists WHERE id = ?";
        ListEntity list = jdbcTemplate.queryForObject(sql, this::mapRowToList, id);

        List<Card> cards = getCardsForList(id);
        list.setCards(cards);

        return list;
    }

    public List<ListEntity> getListsByBoardId(Integer boardId) {
        String sql = "SELECT * FROM lists WHERE board_id = ?";
        List<ListEntity> lists = jdbcTemplate.query(sql, this::mapRowToList, boardId);

        for (ListEntity list : lists) {
            List<Card> cards = getCardsForList(list.getId());
            list.setCards(cards);
        }

        return lists;
    }

    public int createList(ListEntity list) {
        String sql = "INSERT INTO lists (name, board_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, list.getName(), list.getBoardId());
    }

    public int updateList(ListEntity list) {
        String sql = "UPDATE lists SET name = ?, board_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, list.getName(), list.getBoardId(), list.getId());
    }

    public int deleteList(Integer id) {
        String sql = "DELETE FROM lists WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

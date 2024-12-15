package com.dmihelj.list_app.service;

import com.dmihelj.list_app.dao.CardDao;
import com.dmihelj.list_app.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardDao cardDao;

    public int createCard(Card card) {
        return cardDao.createCard(card);
    }

    public List<Card> getCardsByListId(Integer listId) {
        return cardDao.getCardsByListId(listId);
    }

    public Card getCardById(Integer id) {
        return cardDao.getCardById(id);
    }

    public int updateCard(Card card) {
        return cardDao.updateCard(card);
    }

    public int deleteCard(Integer id) {
        return cardDao.deleteCard(id);
    }
}

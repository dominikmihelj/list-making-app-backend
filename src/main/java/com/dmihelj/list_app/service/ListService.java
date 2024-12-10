package com.dmihelj.list_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dmihelj.list_app.dao.ListDao;
import com.dmihelj.list_app.model.ListEntity;

import java.util.List;

@Service
public class ListService {

    @Autowired
    private ListDao listDao;

    // Create a new list
    public int createList(ListEntity listEntity) {
        return listDao.createList(listEntity);
    }

    // Get list by ID
    public ListEntity getListById(Integer id) {
        return listDao.getListById(id);
    }

    // Get all lists
    public List<ListEntity> getListsByBoardId(Integer boardId) {
        return listDao.getListsByBoardId(boardId);
    }

    // Update list
    public int updateList(Integer id, ListEntity listEntity) {
        listEntity.setId(id);  // Set the ID from path into the entity
        return listDao.updateList(listEntity);
    }

    // Delete list
    public int deleteList(Integer id) {
        return listDao.deleteList(id);
    }
}


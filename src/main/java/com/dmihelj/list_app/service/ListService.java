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

    public int createList(ListEntity listEntity) {
        return listDao.createList(listEntity);
    }

    public ListEntity getListById(Integer id) {
        return listDao.getListById(id);
    }

    public List<ListEntity> getListsByBoardId(Integer boardId) {
        return listDao.getListsByBoardId(boardId);
    }

    public int updateList(Integer id, ListEntity listEntity) {
        listEntity.setId(id);
        return listDao.updateList(listEntity);
    }

    public int deleteList(Integer id) {
        return listDao.deleteList(id);
    }
}


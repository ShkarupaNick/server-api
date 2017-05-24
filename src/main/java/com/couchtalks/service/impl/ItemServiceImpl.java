package com.couchtalks.service.impl;

import com.couchtalks.dao.ItemDao;
import com.couchtalks.entity.Item;
import com.couchtalks.service.ItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by ShkarupaN on 15.03.2017.
 */
@Service

public class ItemServiceImpl implements ItemService {
    private final static Logger logger = Logger.getLogger(ItemServiceImpl.class);


    @Autowired
    private ItemDao itemDao;

    @Override
    public List<Item> getByDate(String date) {
        return itemDao.findByDate(date);
    }

    @Override
    public void saveItemList(List<Item> items) {
        for (Item item : items) {
            try {
                itemDao.save(item);
            } catch (Exception e) {
                logger.error("Error while trying to save item do DB. Error message: " + e.getMessage());
            }
        }
    }

    @Override
    public void saveItem(Item item) {

            try {
                itemDao.save(item);
            } catch (Exception e) {
                logger.error("Error while trying to save item do DB. Error message: " + e.getMessage());
            }
    }

    public List<Item> getItemList() {
        return itemDao.findAll();
    }

    @Override
    public String findDistDate(String airdate) {
        return itemDao.findDistDate(airdate);
    }

    @Override
    public void deleteAll() {
        itemDao.deleteAll();
    }

    @Override
    public Item getByUUID(String uuid) {
        return itemDao.findOne(UUID.fromString(uuid));
    }

    @Override
    public List<Item> getLiveItem() {
        return itemDao.getLiveItems();
    }

    @Override
    public List<Item> getTop20MostViewedItems() {
       return itemDao.getMostViewed();
    }


}

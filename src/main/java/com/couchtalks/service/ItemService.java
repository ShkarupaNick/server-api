package com.couchtalks.service;

import com.couchtalks.entity.Item;

import java.util.List;

/**
 * Created by ShkarupaN on 15.03.2017.
 */
public interface ItemService {
   public List<Item> getByDate(String date);

   public void saveItemList(List<Item> items);
   public List<Item> getItemList();
   public String findDistDate(String airdate);


   public void deleteAll();

   public Item getByUUID(String uuid);

   public List<Item> getLiveItem();
   public List<Item> getTop20MostViewedItems();

   public void saveItem(Item item);
}

package com.couchtalks.dao;

import com.couchtalks.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Created by ShkarupaN on 15.03.2017.
 */
public interface ItemDao extends JpaRepository<Item, UUID> {
    @Query("SELECT i FROM Item i WHERE i.airdate = :airdate")
    public List<Item> findByDate(@Param("airdate") String airdate);

    @Query("SELECT distinct i.airdate FROM Item i WHERE i.airdate = :airdate")
    public String findDistDate(@Param("airdate") String airdate);
}

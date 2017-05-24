package com.couchtalks.dao;

import com.couchtalks.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
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

    //@Query("select i  from item i where (current_timestamp AT TIME ZONE 'UTC') between (airstamp::timestamptz AT TIME ZONE 'UTC') AND ((airstamp::timestamptz AT TIME ZONE 'UTC') + cast(runtime||' minute' as interval))")
    @Query(value = "select i.* from item i where current_timestamp between  cast(airstamp as timestamptz) and cast(airstamp as timestamptz) + cast(runtime||' minute' as interval)", nativeQuery = true)
    public List<Item> getLiveItems();


    @Query(value = "select * from item i ORDER BY viewsCnt DESC OFFSET 0 LIMIT 20", nativeQuery = true)
    public List<Item> getMostViewed();
}

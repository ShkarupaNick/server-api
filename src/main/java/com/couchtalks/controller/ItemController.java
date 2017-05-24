package com.couchtalks.controller;

import com.couchtalks.dao.JacksonMapper;
import com.couchtalks.entity.Item;
import com.couchtalks.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.couchtalks.entity.ErrorResponse;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ShkarupaN on 15.03.2017.
 */
@Controller
public class ItemController {
    private final static Logger logger = Logger.getLogger(ItemController.class);

    @Autowired
    ItemService itemService;

    @CrossOrigin
    @RequestMapping(value = "/forbidden", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity forbidden() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<List<Item>>(headers, HttpStatus.FORBIDDEN);
    }

    @CrossOrigin
    @RequestMapping(value = "/item/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity listItems(@RequestParam(value = "date",required = false) String date) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        List<Item> items;
        if (null==date){
            items = itemService.getItemList();
        } else {
            items = itemService.getByDate(date);
        }
        if (items == null) {
            logger.error("Items not found");
            return new ResponseEntity<Item>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Item>>(items, headers, HttpStatus.OK);
    }

    @CrossOrigin
    @Transactional
    @RequestMapping(value = "/item/execute", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loadItems(@RequestParam("action") String action, @RequestParam("date") String date) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = JacksonMapper.getInstance();
        logger.info("mapper " + mapper);
        URL url = new URL("http://api.tvmaze.com/schedule?date=" + date);
        logger.info(url);
        if (itemService.findDistDate(date)==null) {
            Item[] items = mapper.readValue(url, Item[].class);
            itemService.saveItemList(Arrays.asList(items));
            return new ResponseEntity<Void>(headers, HttpStatus.OK);
        } else {
            ErrorResponse error = new ErrorResponse();
            error.setErrorCode("204");
            error.setMessage("Data for date " + date + " already loaded. Please, specify another date.");
            return new ResponseEntity<ErrorResponse>(error, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/item/live", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loadLiveItems() {
        HttpHeaders headers = new HttpHeaders();
        List<Item> items = itemService.getLiveItem();
        if (items == null) {
            ErrorResponse error = new ErrorResponse();
            error.setErrorCode("204");
            error.setMessage("Live items not found");
            return new ResponseEntity<ErrorResponse>(error, headers, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Item>>(items, headers, HttpStatus.OK);
    }

    public ResponseEntity deleteAll(){
        HttpHeaders headers = new HttpHeaders();
        itemService.deleteAll();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
}

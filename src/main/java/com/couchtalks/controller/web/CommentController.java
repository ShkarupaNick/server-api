package com.couchtalks.controller.web;

import com.couchtalks.entity.ErrorResponse;
import com.couchtalks.entity.User;
import com.couchtalks.entity.web.Comment;
import com.couchtalks.service.CommentService;
import com.couchtalks.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShkarupaN on 09.04.2017.
 */

@Controller
public class CommentController {
    private final static Logger logger = Logger.getLogger(CommentController.class);


    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/comment/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity listItems(@RequestParam(value = "itemId", required = false) String itemId, @RequestParam(value = "topN", required = false) Integer topN) throws Exception {
        HttpHeaders headers = new HttpHeaders();

        try {
            List<Comment> comments = null;
            if (null == itemId) {
                comments = commentService.findAll();
            } else if (null != topN) {
                comments = commentService.findTopComments(itemId, topN);
            } else {
                comments = commentService.findAllByEntityId(itemId);
            }
            if (comments == null) {
                comments = new ArrayList<Comment>();
            }
            return new ResponseEntity<List<Comment>>(comments, headers, HttpStatus.OK);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("204", e.getMessage(), sw.toString()), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional
    @RequestMapping(value = "/comment/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registrationJson(@RequestBody Comment comment) {
        logger.info(comment);
        HttpHeaders headers = new HttpHeaders();

        try {
            /*User user= userService.findOne(comment.getUser().getId());
            comment.setUser(user);*/
            commentService.saveComment(comment);
            return new ResponseEntity<Void>(headers, HttpStatus.OK);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("204", e.getMessage(), sw.toString()), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

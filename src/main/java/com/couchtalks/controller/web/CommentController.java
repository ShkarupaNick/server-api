package com.couchtalks.controller.web;

import com.couchtalks.Utils;
import com.couchtalks.entity.Item;
import com.couchtalks.entity.User;
import com.couchtalks.entity.web.ajax.CreateCommentRequest;
import com.couchtalks.entity.web.ajax.LikeSearchCriteria;
import com.couchtalks.entity.ErrorResponse;
import com.couchtalks.entity.web.Comment;
import com.couchtalks.service.CommentService;
import com.couchtalks.service.ItemService;
import com.couchtalks.service.LikesService;
import com.couchtalks.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

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

    @Autowired
    private ItemService itemService;

    @Autowired
    private LikesService likesService;

    @RequestMapping(value = "/comment/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity listItems(@RequestParam(value = "itemId", required = false) String itemId, @RequestParam(value = "topN", required = false) Integer topN) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        logger.info(itemId + " "+ topN);
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
            return new ResponseEntity<>(new ErrorResponse("204", e.getMessage(), Utils.parseException(e)), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/comment/test", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity listItems() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        try {

            Comment root = new Comment();

            root.setText("Самый первый и крутой комменатрий, который все с удовольствием читают и ставят на него лайки. а также дают на него ответ в комментарии - комментария. И так пока ен надоест. Скорее всего прийдется пилить рекурсией. и это меня напрягает.");
            root.setUser(userService.findByUsername("syma"));
            root.setItem(itemService.getByUUID("c0a8143b-5dc6-152d-815d-c6a6182c001c"));

            Comment child = new Comment();
            child.setUser(userService.findByUsername("syma"));
            child.setItem(itemService.getByUUID("c0a8143b-5dc6-152d-815d-c6a6182c001c"));
            child.setText("bla bla bla bla bla bla");

            Comment child2 = new Comment();
            child2.setUser(userService.findByUsername("syma"));
            child2.setItem(itemService.getByUUID("c0a8143b-5dc6-152d-815d-c6a6182c001c"));
            child2.setText("12222222222222222222222 222222222222222222222222 222222222222222222222222222 2222222222222 2222222222222222222222222");

            List<Comment> children = new ArrayList<>();
            children.add(child);
            children.add(child2);
            root.setChildren(children);
            commentService.saveComment(root);

            return new ResponseEntity<Void>(headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("204", e.getMessage(), Utils.parseException(e)), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/comment/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity saveComment(@RequestBody CreateCommentRequest commentRequest, Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        User user = null;
        if (principal != null) {
            user = userService.findByEmail(principal.getName());
        }
        logger.debug(user);
        logger.debug(commentRequest);

        try {
            Comment comment = new Comment();
            comment.setUser(user);
            comment.setText(commentRequest.getText());
            Item item = itemService.getByUUID(commentRequest.getItem().getUuid().toString());
            comment.setItem(item);
            comment = commentService.saveComment(comment);

            Comment parent = commentService.findOne(commentRequest.getParent().getUuid());

            if(null!=parent){
                parent.addChildren(comment);
                commentService.saveComment(parent);
            }
            logger.debug(comment);
            return new ResponseEntity<Comment> (comment, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("204", e.getMessage(), Utils.parseException(e)), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/comment/like", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Comment like(@RequestBody LikeSearchCriteria paramMap){
        Comment comment = commentService.like(paramMap.getCommentUUID(), paramMap.getUserId());
        logger.debug(comment);
        return comment;
    }

    @RequestMapping(value = "/comment/dislike", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Comment dislike(@RequestBody LikeSearchCriteria paramMap){
        Comment comment = commentService.disLike(paramMap.getCommentUUID(), paramMap.getUserId());
        logger.debug(comment);
        return comment;
    }
}
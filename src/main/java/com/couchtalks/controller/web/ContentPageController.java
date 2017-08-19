package com.couchtalks.controller.web;

import com.couchtalks.entity.Item;
import com.couchtalks.entity.User;
import com.couchtalks.entity.web.Comment;
import com.couchtalks.entity.web.Likes;
import com.couchtalks.service.CommentService;
import com.couchtalks.service.ItemService;
import com.couchtalks.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

/**
 * Created by ShkarupaN on 24.04.2017.
 */

@Controller
public class ContentPageController {
    private final static Logger logger = Logger.getLogger(ContentPageController.class);

    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;
    @Autowired
    CommentService commentService;

    @RequestMapping(value = {"/content_page"}, method = RequestMethod.GET)
    public String contentPage(@RequestParam(name = "uuid") String uuid, Model model, Principal principal) {
        User user = null;
        if (principal != null) {
            user = userService.findByEmail(principal.getName());
        } else {
            user = new User();
        }

        Item item = itemService.getByUUID(uuid);
        if (null==item){
            return "index";
        }
        List<Comment> comments = commentService.findRootEntityId(uuid);
        fillLikes(comments, user);
        List<Comment> topComments = commentService.findTopComments(uuid,10);
        fillLikes(topComments, user);

        model.addAttribute("item", item);
        item.addViewsCnt();
        itemService.saveItem(item);
        model.addAttribute("comments",comments);
        model.addAttribute("topComments", topComments);
        model.addAttribute("userForm", user);
        model.addAttribute("user", user);

        return "contentPage";
    }

    private void fillLikes (List<Comment> comments, User user){
        for (Comment comment: comments) {
            Long userId = user.getId();
            boolean isLike = comment.getLikes().stream().filter((p)-> p.getUser().getId().equals(userId)).count()>0;
            if(isLike){
                comment.setLikedByCurrentUser(true);
            }
            if(comment.getChildren()!=null&&comment.getChildren().size()>0){
                fillLikes(comment.getChildren(), user);
            }
        }
    }
}

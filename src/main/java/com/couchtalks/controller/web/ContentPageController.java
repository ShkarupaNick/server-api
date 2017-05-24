package com.couchtalks.controller.web;

import com.couchtalks.entity.Item;
import com.couchtalks.entity.User;
import com.couchtalks.entity.web.Comment;
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
            user = userService.findByUsername(principal.getName());
        } else {
            user = new User();
        }

        Item item = itemService.getByUUID(uuid);
        List<Comment> comments = commentService.findAllByEntityId(uuid);
        logger.info("comments: "+comments);
        model.addAttribute("comments",comments);
        logger.info(comments);
        model.addAttribute("item", item);
        item.addViewsCnt();
        itemService.saveItem(item);
        model.addAttribute("userForm", user);
        model.addAttribute("user", user);

        return "contentPage";
    }
}

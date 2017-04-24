package com.couchtalks.controller;

import com.couchtalks.entity.User;
import com.couchtalks.service.UserService;
import com.couchtalks.validator.RegistrationValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;

/**
 * Created by ShkarupaN on 13.04.2017.
 */

@Controller
public class ProfileControler {

    private final static Logger logger = Logger.getLogger(ProfileControler.class);
    @Autowired
    private UserService userService;
    @Autowired
    RegistrationValidator validator;


    @RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
    public String profile(Model model, Principal principal) {
        User user;
        if (principal != null) {
            user = userService.findByUsername(principal.getName());
            user.setRepeatPassword(user.getPassword());
        } else {
            user = new User();
        }


        model.addAttribute("userForm", user);

       /* model.addAttribute("profilePicture", user.getProfilePicture()!=null?new String(user.getProfilePicture()):defaultProfileImage);*/

        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String singleFileUpload(@ModelAttribute("userForm") User userForm, Model model, Principal principal, BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return "profile";
        }
        User user = null;
        if (principal != null) {
            user = userService.findByUsername(principal.getName());
            logger.debug("User from DB: "+ user);
            user.setEmail(userForm.getEmail());
            user.setUsername(userForm.getUsername());
            if (null != userForm.getProfilePictureFile()) {
                byte[] base64 = Base64.getEncoder().encode(userForm.getProfilePictureFile().getBytes());
                user.setProfilePicture(base64);
            }
            logger.debug("User from Form: "+ user);
            model.addAttribute("userForm", user);
        }
        userService.update(user);
        return "profile";
    }
}

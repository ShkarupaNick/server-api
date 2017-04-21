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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
        } else {
            user = new User();
        }
        model.addAttribute("userForm", user);
        return "profile";
    }

    @RequestMapping(value = "/loadavatar", method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String loadAvatar(/*@RequestParam("profilePicture") MultipartFile profilePicture,*/@RequestParam("userForm") User userForm, Model model, Principal principal, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "profile";
        }

        User user = null;
        if (principal != null) {
            user = userService.findByUsername(principal.getName());
            if (null != userForm.getProfilePicture()) {
                try {
                    byte[] base64 = Base64.getEncoder().encode(userForm.getProfilePictureFile().getBytes());
                    String base64DataString = new String(base64, "UTF-8");
                    model.addAttribute("user_avatar", base64DataString);
                    user.setProfilePicture(userForm.getProfilePicture());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            model.addAttribute("user", user);
        }

        userService.save(user);
        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String singleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userForm") User userForm, Model model, Principal principal, BindingResult bindingResult) {
        User user = null;
        if (principal != null) {
            user = userService.findByUsername(principal.getName());
            user.setEmail(userForm.getEmail());
            user.setPassword(userForm.getPassword());
            user.setUsername(userForm.getUsername());
            model.addAttribute("user", user);
        }
        validator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "profile";
        }
        userService.save(user);
        return "profile";
    }
}

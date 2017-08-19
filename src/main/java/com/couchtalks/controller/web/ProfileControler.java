package com.couchtalks.controller.web;

import com.couchtalks.entity.User;
import com.couchtalks.entity.web.UserForm;
import com.couchtalks.service.UserService;
import com.couchtalks.validator.ProfileValidator;
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
    ProfileValidator validator;


    @RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
    public String profile(Model model, Principal principal) {
        User user;
        if (principal != null) {
            user = userService.findByEmail(principal.getName());
        } else {
            user = new User();
        }

        UserForm userForm = new UserForm(user);
        userForm.setPassword(null);
        userForm.setRepeatPassword(null);

        model.addAttribute("userForm", userForm);
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String singleFileUpload(@ModelAttribute("userForm") UserForm userForm, Model model, Principal principal, BindingResult bindingResult) throws IOException {

        validator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "profile";
        }
        User user = null;
        if (principal != null) {
            user = userService.findByEmail(principal.getName());
            user.setEmail(userForm.getEmail());
            if (!userForm.getProfilePictureFile().isEmpty()) {
                byte[] base64 = Base64.getEncoder().encode(userForm.getProfilePictureFile().getBytes());
                user.setProfilePicture(base64);
            }
            if (!userForm.getNewPassword().isEmpty()) {
                user.setPassword(userForm.getNewPassword());
                userForm.setRepeatPassword(null);
                userForm.setNewPassword(null);
                userForm.setPassword(null);
                return "profile";
            }
            userService.update(user);
            model.addAttribute("userForm", userForm);
            model.addAttribute("user", user);
        }
        return "profile";
    }
}

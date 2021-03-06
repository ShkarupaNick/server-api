package com.couchtalks.controller;

import com.couchtalks.entity.Item;
import com.couchtalks.entity.Role;
import com.couchtalks.entity.User;
import com.couchtalks.entity.web.UserForm;
import com.couchtalks.service.ItemService;
import com.couchtalks.service.SecurityService;
import com.couchtalks.service.UserService;
import com.couchtalks.validator.RegistrationValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controller for {@link com.couchtalks.entity.User}'s pages.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Controller
public class UserController {
    private final static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    ItemService itemService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    RegistrationValidator validator;


    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registrationJson(@RequestBody User user) {
        userService.save(user);
        securityService.autoLogin(user.getUsername(), user.getPassword());
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        logger.info("/registration GET");
        model.addAttribute("userForm", new UserForm());
        return "registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model model) {
        logger.info("/registration POST");
        validator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        Role role = new Role();
        role.setName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setUsername(userForm.getUsername());

        user.setRoles(roles);
        userService.save(user);
        securityService.autoLogin(userForm.getEmail(), userForm.getPassword());
        return "redirect:/";
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity listUsers() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        List<User> users = userService.findAll();
        if (users == null) {
            logger.error("Users not found");
            return new ResponseEntity<User>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(users, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity listUsers2() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        List<User> users = userService.findAll();
        if (users == null) {
            logger.error("Users not found");
            return new ResponseEntity<User>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(users, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity logout() throws Exception {
        securityService.logout();
        return new ResponseEntity<List<User>>(new HttpHeaders(), HttpStatus.OK);
    }


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String login(Model model, Principal principal) {
        logger.info("\"/\", \"/index\" GET");
        SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
        List<Item> items = itemService.getTop20MostViewedItems();
        List<Item> liveItems = itemService.getLiveItem();
        model.addAttribute("liveItems",liveItems);
        model.addAttribute("items", items);
        model.addAttribute("userForm", new User());
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("user", user);
        }
        return "index";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String loginForm(@ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        logger.error(userForm);
        securityService.autoLogin(userForm.getEmail(), userForm.getPassword());
        if (userForm != null) {
            User user = userService.findByEmail(userForm.getEmail());
            model.addAttribute("user", user);
        }

        List<Item> items = itemService.getTop20MostViewedItems();
        List<Item> liveItems = itemService.getLiveItem();
        model.addAttribute("items", items);
        model.addAttribute("liveItems", liveItems);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity login(@RequestBody User user) throws Exception {
        securityService.autoLogin(user.getUsername(), user.getPassword());
        return new ResponseEntity<String>("{\"SessionId\":\"" + RequestContextHolder.currentRequestAttributes().getSessionId() + "\"}", new HttpHeaders(), HttpStatus.OK);
    }
}

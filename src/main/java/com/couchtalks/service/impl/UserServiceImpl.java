package com.couchtalks.service.impl;

import com.couchtalks.dao.RoleDao;
import com.couchtalks.dao.UserDao;
import com.couchtalks.entity.Role;
import com.couchtalks.entity.User;
import com.couchtalks.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    final static Logger logger = Logger.getLogger(UserServiceImpl.class);


    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findAll().get(0));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        user = userDao.findByUsername(user.getUsername());
        if (null != user) {
            userDao.save(user);
        }
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userDao.findOne(id);
    }
}

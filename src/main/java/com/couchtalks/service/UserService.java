package com.couchtalks.service;

import com.couchtalks.entity.User;

import java.util.List;

/**
 * Service class for {@link com.couchtalks.entity.User}
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface UserService {

    void save(User user);
    void update(User user);

    User findByUsername(String username);
    List<User> findAll();
    User findOne(Long id);

}

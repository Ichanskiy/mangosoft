package com.mangosoft.service;

import com.mangosoft.entity.*;
import org.springframework.data.domain.Page;

public interface UserService {

    User findUserByEmail(String email);
    User saveUser(User user);
    Page<User> getUsersByPageAndCount(int page, int size);
    User updateUser(User user);
    void deleteUser(User user);
    boolean comparePassword(String oldPassword, User user);

    default boolean verifyCountPassword(User user) {
        return user.getPassword().length() > 4;
    }

}

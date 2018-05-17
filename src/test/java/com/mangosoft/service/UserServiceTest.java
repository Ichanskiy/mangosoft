package com.mangosoft.service;


import com.mangosoft.entity.User;
import com.mangosoft.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("get users by page and count")
    @Test
    void getUsersByPageAndCount() {
        userRepository.deleteAll();
        createUsers();
        List<User> users0 = userService.getUsersByPageAndCount(0, 5).getContent();
        Page<User> users1 =  userService.getUsersByPageAndCount(1, 5);
        Page<User> users10 =  userService.getUsersByPageAndCount(10, 5);
        assertEquals(users0.size(), 5);
        assertEquals(users1.getTotalElements(), 20);
        assertEquals(users1.getTotalPages(), 4);
        assertTrue(users10.getContent().isEmpty());
    }

    @DisplayName("update user")
    @Test
    void updateUser() {
        User user = new User();
        user.setEmail("email");
        user.setPassword("000");
        User userDb = userService.saveUser(user);
        userDb.setEmail("newEmail");
        userDb.setPassword("123");
        userDb.setName("name");
        User userUpdated = userService.updateUser(userDb);
        assertEquals(userUpdated.getEmail(), "newEmail");
        assertEquals(userUpdated.getName(), "name");
        assertTrue(userService.comparePassword("123", userUpdated));
    }

    private void createUsers(){
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setEmail("email".concat(String.valueOf(i)));
            user.setPassword("123".concat(String.valueOf(i)));
            userService.saveUser(user);
        }
    }
}

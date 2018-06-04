package com.mangosoft.repository;

import com.mangosoft.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("find one user by email")
    @ParameterizedTest
    @ValueSource(strings = { "save1@mail.com", "save2@mail.com" })
    void findUserByEmail(String inputEmail) {
        User user = new User();
        user.setEmail(inputEmail);
        userRepository.saveAndFlush(user);
        User userDb = userRepository.findByEmail(user.getEmail());
        assertEquals(user, userDb);
    }

    @Test
    @DisplayName("save test user")
    void saveUser() {
        User user = new User();
        user.setEmail("user@mail.com");
        user.setPassword("1111");
        User userDb = userRepository.saveAndFlush(user);
        assertEquals(user, userDb);
    }

    @Test
    @DisplayName("delete user")
    void deleteByEmail() {
        userRepository.deleteAll();
        User user = new User();
        user.setEmail("user@mail.com");
        User userDb = userRepository.saveAndFlush(user);
        userRepository.delete(userDb);
        List<User> users = userRepository.findAll();
        assertTrue(users.isEmpty());
    }

}
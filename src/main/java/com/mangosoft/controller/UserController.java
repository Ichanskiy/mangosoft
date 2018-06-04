package com.mangosoft.controller;


import com.mangosoft.entity.User;
import com.mangosoft.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ControllerAPI.USER_CONTROLLER)
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping(value = ControllerAPI.GENERAL_REQUEST)
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        log.info("Update user...");
        User userDb = userService.findUserByEmail(user.getEmail());
        if (userDb == null) {
            log.info("User not found");
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
        userService.updateUser(userDb);
        log.info("User is updated");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = ControllerAPI.GENERAL_REQUEST)
    public ResponseEntity<User> deleteUser(@Valid @RequestBody User user) {
        log.info("Delete user...");
        User userDb = userService.findUserByEmail(user.getEmail());
        if (userDb == null) {
            log.info("User not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.deleteUser(userDb);
        log.info("User is deleted");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = ControllerAPI.GENERAL_REQUEST)
    public ResponseEntity<List<User>> getUserByPageAndNumber(@PathVariable("page") Integer page,
                                                                       @PathVariable("size") Integer size) {
        log.info("Get users by page and count");
        Page<User> users = userService.getUsersByPageAndCount(page, size);
        return new ResponseEntity<>(users.getContent(), HttpStatus.OK);
    }
}

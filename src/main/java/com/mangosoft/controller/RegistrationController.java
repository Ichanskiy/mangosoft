package com.mangosoft.controller;

import com.mangosoft.entity.User;
import com.mangosoft.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class RegistrationController {

	@Autowired
	private UserService userService;

	@PostMapping(value = ControllerAPI.REGISTRATION)
	public ResponseEntity<User> createNewUser(@Valid User user, BindingResult bindingResult) {
		log.info("Creating new user...");
		User userExists = userService.findUserByEmail(user.getEmail());
		if (!userService.verifyCountPassword(user)) {
			log.info("User password length must be at least 4 characters");
			bindingResult
					.rejectValue("email", "error.user",
							"Password length must be at least 4 characters");
		}
		if (userExists != null) {
			log.info("There is already a user registered with the email provided");
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			userService.saveUser(user);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}

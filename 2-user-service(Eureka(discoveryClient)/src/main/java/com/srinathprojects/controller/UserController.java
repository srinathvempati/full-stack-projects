package com.srinathprojects.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.srinathprojects.dto.CredentialDto;
import com.srinathprojects.dto.UserDto;
import com.srinathprojects.models.User;
import com.srinathprojects.service.CredentialService;
import com.srinathprojects.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	CredentialService credentialService;

	Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserDto createUser(@Valid @RequestBody UserDto userDto) {

		log.info("User Controller :: CreateUser {}", userDto);

		return userService.save(userDto);
	}

	@GetMapping("/{userId}")
	public UserDto fetchUser(@PathVariable Integer userId) {
		log.info("User Controller :: fetchUser {}", userId);

		return userService.findById(userId);
	}
	
	@PutMapping("/{userId}")
	public UserDto updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {
		
		log.info("User Controller :: updateUser {}", userId);

		return userService.updateUser(userId, userDto);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable Integer userId) {
		
		log.info("User Controller :: Delete User {}", userId);
		
		userService.deleteUser(userId);
	}
	
	@GetMapping("/username/{userName}")
	public CredentialDto fetchByUserName(@PathVariable String userName) {
		
		log.info("User Controller :: fetchByUserName {}", userName);

		return credentialService.findByUsername(userName);
	}
	
	
	@GetMapping
	public List<User> fetchAll() {
		

		return userService.fetchAllUser();
	}

}

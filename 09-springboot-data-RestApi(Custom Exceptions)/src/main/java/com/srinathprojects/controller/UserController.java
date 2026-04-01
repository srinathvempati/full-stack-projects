package com.srinathprojects.controller;

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

import com.srinathprojects.dto.UserDto;
import com.srinathprojects.entity.User;
import com.srinathprojects.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	// DTO class will use only for insertion oder.
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public  UserDto createUser(@RequestBody UserDto userDto) {
		
		log.info("User Controller :: CreateUser {}", userDto);
		
		return userService.save(userDto);
	} 
	
	@GetMapping("/{userId}")
	public User fetchUser(@PathVariable Integer userId ) {
		log.info("User Controller :: fetchUser {}", userId);
		
		return userService.findById(userId);
	}
	
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable Integer userId, @RequestBody User user ) {
		log.info("User Controller :: updateUser {} {} {} {}", user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone());
		
		return userService.UpdateUser(userId, user);
	}
	
	@DeleteMapping("/{userId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Integer userId ) {
		log.info("User Controller :: deleteUser {}", userId);
		
		userService.deleteUser(userId);
	}
	
	

}

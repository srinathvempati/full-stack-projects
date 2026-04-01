package com.srinathprojects.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.srinathprojects.entity.User;
import com.srinathprojects.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		log.info("User Controller :: CreateUser {}", user);
		return userService.save(user);
	}
	
	@GetMapping("/{userId}")
	public User fetchUser(@PathVariable Integer userId){
		log.info("User Controller :: fetchUser {}", userId);
		return  userService.findById(userId);
	}
	
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable Integer userId, @RequestBody User user){
		log.info("User Controller :: updateUser {} {} {} {}", user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhone());
		return  userService.Update(userId, user);
	}
	
	@DeleteMapping("/{userId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Integer userId){
		log.info("User Controller :: deleteUser {}", userId);
		  userService.delete(userId);
	}

}

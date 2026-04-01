package com.srinathprojects.service;

import org.springframework.stereotype.Service;

import com.srinathprojects.dto.UserDto;
import com.srinathprojects.entity.User;


public interface UserService {
	
	
	public UserDto save(UserDto userDto);
	public User findById(Integer userId);
	public UserDto updateUser(Integer userId, UserDto userDto);
	public void deleteUser(Integer userId);
	

}

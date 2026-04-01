package com.srinathprojects.service;


import java.util.List;
import java.util.Optional;

import com.srinathprojects.dto.UserDto;
import com.srinathprojects.models.User;


public interface UserService {
	
	
	public UserDto save(UserDto userDto);
	public UserDto findById(Integer userId);
	public UserDto updateUser(Integer userId, UserDto userDto);
	public void deleteUser(Integer userId);
	public List<User> fetchAllUser();
	

}

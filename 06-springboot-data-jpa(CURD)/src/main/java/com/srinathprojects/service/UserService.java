package com.srinathprojects.service;


import com.srinathprojects.entity.User;

public interface UserService {
	
	
	User save(User user);
	User findById(Integer userId);
	User Update(Integer userId, User user);
	void delete(Integer userId);
	
	
	

}

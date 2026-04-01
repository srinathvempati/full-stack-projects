
package com.srinathprojects.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinathprojects.dto.UserDto;
import com.srinathprojects.entity.User;
import com.srinathprojects.repository.UserRepository;
import com.srinathprojects.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto save(UserDto  userDto) {
		
		User dbUser = new User();
		
		dbUser.setFirstName(userDto.getFName());
		dbUser.setLastName(userDto.getLName());
		dbUser.setEmail(userDto.getEmailId());
		dbUser.setPhone(userDto.getPhoneNumber());
		dbUser.setUserId(userDto.getId());
		
		userRepository.save(dbUser);
		
		
		UserDto userDt = new UserDto();
		
		userDt.setFName(dbUser.getFirstName());
		userDt.setLName(dbUser.getLastName());
		userDt.setEmailId(dbUser.getEmail());
		userDt.setPhoneNumber(dbUser.getPhone());
		userDt.setId(dbUser.getUserId());
		
		
		
		
		
		return userDt;
	}

	@Override
	public User findById(Integer userId) {
		
		return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found in DB"));
	}

	@Override
	public User UpdateUser(Integer userId, User user) {
		User dbUser = findById(userId);
		
		dbUser.setFirstName(user.getFirstName());
		dbUser.setLastName(user.getLastName());
		dbUser.setEmail(user.getEmail());
		dbUser.setPhone(user.getPhone());
		
		userRepository.save(dbUser);
		
		
		return dbUser;
	}

	@Override
	public void deleteUser(Integer userId) {
		User dbUser = findById(userId);
		
		userRepository.delete(dbUser);
		
	}
	
	

}

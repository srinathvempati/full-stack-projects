
package com.srinathprojects.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinathprojects.dto.UserDto;
import com.srinathprojects.entity.User;
import com.srinathprojects.exception.UserNotFoundException;
import com.srinathprojects.repository.UserRepository;
import com.srinathprojects.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto save(UserDto userDto) {

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

		return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found in DB"));
	}

	@Override
	public UserDto updateUser(Integer userId, UserDto userDto) {
		User userDb = findById(userId);

		userDb.setFirstName(userDto.getFName());
		userDb.setLastName(userDto.getLName());
		userDb.setEmail(userDto.getEmailId());
		userDb.setPhone(userDto.getPhoneNumber());

		userRepository.save(userDb);

		UserDto userDt = new UserDto();

		userDt.setId(userDb.getUserId());
		userDt.setFName(userDb.getFirstName());
		userDt.setLName(userDb.getLastName());
		userDt.setEmailId(userDb.getEmail());
		userDt.setPhoneNumber(userDb.getPhone());

		return userDt;
	}

	@Override
	public void deleteUser(Integer userId) {
		User dbUser = findById(userId);

		userRepository.delete(dbUser);

	}

}

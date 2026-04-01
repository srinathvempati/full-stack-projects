package com.srinathprojects.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinathprojects.dto.UserDto;
import com.srinathprojects.mappers.UserMapper;
import com.srinathprojects.models.Credential;
import com.srinathprojects.models.User;
import com.srinathprojects.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDto save(UserDto userDto) {
		

		log.info("User service :: save user Details {}", userDto.getEmailAddress());

		User user = userMapper.toEntity(userDto);

		Credential credential = user.getCredential();

		credential.setUser(user);

		User dbUser = userRepository.save(user);

		return userMapper.toDto(dbUser);
	}

	@Override
	public UserDto findById(Integer userId) {
		
		log.info("User service :: findById {}", userId);

		return userRepository.findById(userId).map(userMapper::toDto)
				.orElseThrow(() -> new RuntimeException("user not found in DB"));
	}

	@Override
	public UserDto updateUser(Integer userId, UserDto userDto) {
		
		
		log.info("User service :: updateUser {}", userId);

	    User existingUser = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("user not found in DB"));

	    existingUser.setFirstName(userDto.getFirstName());
	    existingUser.setLastName(userDto.getLastName());
	    existingUser.setEmail(userDto.getEmailAddress());
	    existingUser.setPhone(userDto.getContact());

	    if (userDto.getCredential() != null) {
	        Credential existingCredential = existingUser.getCredential();

	        if (existingCredential != null) {
	            //existingCredential.setUserName(userDto.getCredential().getUserName());
	            existingCredential.setPassword(userDto.getCredential().getPassword());
	            existingCredential.setRoleBasedAuthority(userDto.getCredential().getRoleBasedAuthority());
	        } else {
	            Credential newCredential = new Credential();
	            newCredential.setUserName(userDto.getCredential().getUserName());
	            newCredential.setPassword(userDto.getCredential().getPassword());
	            newCredential.setRoleBasedAuthority(userDto.getCredential().getRoleBasedAuthority());
	            newCredential.setUser(existingUser);
	            existingUser.setCredential(newCredential);
	        }
	    }

	    User updatedUser = userRepository.save(existingUser);
	    return userMapper.toDto(updatedUser);
	}

	@Override
	public void deleteUser(Integer userId) {
		
		log.info("User service :: deleteUser {}", userId);

		User existingUser = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("user not found in DB"));
		
		userRepository.delete(existingUser);
	}

	@Override
	public List<User> fetchAllUser() {
	
		log.info("User service :: fetchAllUser");
		
		return  userRepository.findAll();
	}
	

}

package com.srinathprojects.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinathprojects.entity.User;
import com.srinathprojects.repository.UserReposiroty;
import com.srinathprojects.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserReposiroty userReposiroty;

	@Override
	public User save(User user) {
		return userReposiroty.save(user);
	}

	@Override
	public User findById(Integer userId) {
		
		Optional<User> optional = userReposiroty.findById(userId);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new RuntimeException("user not found in DB");
		}
		
	}
	
	// Using Java8
	public User findByIdJava8(Integer userId) {
		return userReposiroty.findById(userId).orElseThrow(() -> new RuntimeException("user not found in DB"));
	}

	@Override
	public User Update(Integer userId, User user) {
		 User dbUser = findById(userId);
		 
		 dbUser.setFirstName(user.getFirstName());
		 dbUser.setLastName(user.getLastName());
		 dbUser.setEmail(user.getEmail());
		 
		 userReposiroty.save(dbUser);
		
		return dbUser;
	}

	@Override
	public void delete(Integer userId) {
		
		User dbUser = findById(userId);
		
		userReposiroty.delete(dbUser);
	}
	
	

}

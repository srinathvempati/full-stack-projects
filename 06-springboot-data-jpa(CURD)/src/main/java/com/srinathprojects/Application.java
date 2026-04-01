package com.srinathprojects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.srinathprojects.entity.User;
import com.srinathprojects.repository.UserReposiroty;
import com.srinathprojects.service.UserService;

@SpringBootApplication
public class Application implements CommandLineRunner{

    private final UserReposiroty userReposiroty;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private User user;


    Application(UserReposiroty userReposiroty) {
        this.userReposiroty = userReposiroty;
    }
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	  
	  // inserting the record. 
	  
		user.setFirstName("srinath");
		user.setLastName("v");
		user.setEmail("srinath@gmail.com");
		user.setPhone("7013542970");
		
		userService.save(user);
		
		
	
		/*
		//get
		
		User user1 = userService.findById(1);
		System.out.println(user1.getFirstName()) ;
		
		*/
		
		/*
		//Delete
		userService.delete(3);
		
		*/
	}

}

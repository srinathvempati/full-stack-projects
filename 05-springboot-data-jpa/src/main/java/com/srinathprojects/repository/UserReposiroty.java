package com.srinathprojects.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srinathprojects.entity.User;

public interface UserReposiroty extends JpaRepository<User, Integer>{

}

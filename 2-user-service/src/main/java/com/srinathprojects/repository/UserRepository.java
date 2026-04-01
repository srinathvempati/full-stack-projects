package com.srinathprojects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srinathprojects.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}

package com.jsp.springbootdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.springbootdemo.entities.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	User findByEmailAndPassword(String email, int password);

//	List<User> findByAgeBetween(int start, int end);

}

package com.jsp.springbootdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.springbootdemo.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{
	List<Student> findByName(String name);
		List<Student> findByAge(int age);
		List<Student> findByyoe(int yoe);
		List<Student> findByAgeBetween(int start, int end);
		List<Student> findByAgeGreaterThan(int age);
		List<Student> findByAgeLessThan(int age);
		
		

}

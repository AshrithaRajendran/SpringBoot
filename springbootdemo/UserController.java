package com.jso.springbootdemo;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.springbootdemo.entities.User;
import com.jsp.springbootdemo.exception.UserNotFoundException;
import com.jsp.springbootdemo.help.Login;
import com.jsp.springbootdemo.repository.UserRepository;

@RestController
public class UserController {
  @Autowired
  UserRepository ur;
 
@PostMapping("/saveuser")
public ReponseStructure<User> saveuser(@RequestBody User user)
{
	ur.save(user);
	ReponseStructure<User> rs=new ReponseStructure<User>();
	rs.setData(user);
	rs.setStatuscode(HttpStatus.CREATED.value());
	rs.setMessage("data inserted successfully");
	return rs;

}
@GetMapping("/fetchdata")
public ReponseStructure<User> fetchdatabyid(@RequestParam("id") int id)
{  
	try {
	Optional<User> option=ur.findById(id);
	User u=option.get();
	ReponseStructure<User> rs=new ReponseStructure<User>();
	rs.setStatuscode(HttpStatus.FOUND.value());
	rs.setData(u);
	rs.setMessage("data inserted successfully");
	return rs;
	}
	catch(NoSuchElementException ns)
	{
		ReponseStructure<User> rs=new ReponseStructure<User>();
		rs.setData(null);
		rs.setMessage("not found");
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		return rs;
	}
}
@PutMapping("/updateUserdata")
public ReponseStructure<User> updateUser(@RequestBody User user)
{
	ur.save(user);
	try {
	ReponseStructure<User> rs=new ReponseStructure<User>();
	rs.setData(user);
	rs.setMessage("data updated");
	rs.setStatuscode(HttpStatus.ACCEPTED.value());
	return rs;
	}
	catch(Exception e)
	{
		ReponseStructure<User> rs=new ReponseStructure<User>();
		rs.setData(null);
		rs.setMessage("not data updated");
		rs.setStatuscode(HttpStatus.NOT_ACCEPTABLE.value());
		return rs;
	}
	
}
@DeleteMapping("deleteUserdata")
public ReponseStructure<User> deleteUser(@RequestParam("id") int id)
{
	Optional<User> option=ur.findById(id);
	User u=option.get();
	ur.deleteById(id);
	ReponseStructure<User> rs=new ReponseStructure<User>();
	rs.setStatuscode(HttpStatus.OK.value());
	rs.setData(u);
	rs.setMessage("data DELETED successfully");
	return rs;
}
//@GetMapping("/fetchdatabetween") 
//public ReponseStructure<List<User>> fetchdatabetween(@RequestParam("start") int start,@RequestParam("end") int end)
//{
//	List<User> users=ur.findByAgeBetween(start,end);
//ReponseStructure<List<User>> rs=new ReponseStructure<List<User>>();
//rs.setStatuscode(HttpStatus.FOUND.value());
//rs.setData(users);
//rs.setMessage("data inserted successfully");
//return rs;
//}
@GetMapping("/validation")
public ReponseStructure<User> loginValidation(@RequestBody Login login)
{
	User u=ur.findByEmailAndPassword(login.getEmail(),login.getPassword());
	if(u!=null)
	{
		ReponseStructure<User> rs=new ReponseStructure<User>();
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setData(u);
		rs.setMessage("user found");
		return rs;
	}
	else {
		ReponseStructure<User> rs=new ReponseStructure<User>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setData(u);
		rs.setMessage("user not found");
		return rs;
		
	}
}
@GetMapping("/fetchuser")
public ReponseStructure<User> fetchUser(@RequestParam("id") int id)
{
	Optional<User> optional=ur.findById(id);
	if(optional.isPresent())
	{
		ReponseStructure<User> rs=new ReponseStructure<User>();
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setData(optional.get());
		rs.setMessage("data found");
		return rs;
	}
	else
	{
		throw new UserNotFoundException("data not found");
	}
}

	
}

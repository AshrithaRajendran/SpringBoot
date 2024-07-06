package com.jso.springbootdemo;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jsp.springbootdemo.entities.Student;
import com.jsp.springbootdemo.repository.StudentRepository;

@RestController//it is the combination of controller and response body
public class StudentController {
	@Autowired
	StudentRepository sr;
	@PostMapping("/save")
	public  ReponseStructure<Student> savestudent(@RequestBody Student student)
	{
		sr.save(student);
		ReponseStructure<Student> rs=new ReponseStructure<Student>();
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setData(student);
		rs.setMessage("data saved successfully");
	    return rs;
	}
	@GetMapping("/fetchDataById")
	public ReponseStructure<Student> fetchDataById(@RequestParam("id") int id)
	{
		try
		{
			Optional<Student> option=sr.findById(id);
			Student s=option.get();
			ReponseStructure<Student> rs=new ReponseStructure<Student>();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(s);
			rs.setMessage("data found");
			return rs;		
		}
	catch(NoSuchElementException ns)
	{
		ReponseStructure<Student> rs=new ReponseStructure<Student>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setData(null);
		rs.setMessage("data not found");
		return rs;	
	}
	}
	@GetMapping("/fetchdatabyname")
	public ReponseStructure<List<Student>> fetchDataByName(@RequestParam("name") String name)
	{
			List<Student> students=sr.findByName(name);
			if(students.size()>0) {
			ReponseStructure<List<Student>> rs=new ReponseStructure<List<Student>>();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(students);
			rs.setMessage("data found");
			return rs;
			}
			else
			{
				ReponseStructure<List<Student>> rs=new ReponseStructure<List<Student>>();
				rs.setStatuscode(HttpStatus.NOT_FOUND.value());
				rs.setData(students);
				rs.setMessage("data not found");
				return rs;
			}
	}
	@GetMapping("/fetchdatabyage")
	public ReponseStructure<List<Student>> fetchDataByName(@RequestParam("age") int  age)
	{
			List<Student> students=sr.findByAge(age);
			ReponseStructure<List<Student>> rs=new ReponseStructure<List<Student>>();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(students);
			rs.setMessage("data found");
			return rs;	
	}
	@GetMapping("/fetchdatabyyoe")
	public ReponseStructure<List<Student>> fetchDataByYoe(@RequestParam("yoe") int yoe)
	{
			List<Student> students=sr.findByyoe(yoe);
			ReponseStructure<List<Student>> rs=new ReponseStructure<List<Student>>();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(students);
			rs.setMessage("data found");
			return rs;	
	}
	@PutMapping("/updatedata")
	public  ReponseStructure<Student> updateStudent(@RequestBody Student student)
	{
		sr.save(student);
		try {
		ReponseStructure<Student> rs=new ReponseStructure<Student>();
		rs.setStatuscode(HttpStatus.ACCEPTED.value());
		rs.setMessage("data updated");
		rs.setData(student);
		return rs;
		} catch (Exception e)
		{
			ReponseStructure<Student> rs=new ReponseStructure<Student>();
			rs.setStatuscode(HttpStatus.NOT_ACCEPTABLE.value());
			rs.setMessage("data not updated");
			rs.setData(null);
			return rs;
		}
	}
	@DeleteMapping("/deletedata")
	public ReponseStructure<Student> deleteStudent(@RequestParam("id") int id)
	{
		Optional<Student> optional=sr.findById(id);
		Student s=optional.get();
		sr.deleteById(id);
		ReponseStructure<Student> rs=new ReponseStructure<Student>();
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setData(s);
		rs.setMessage("data deleted");
		return rs;
	}
	@GetMapping("/fetchdatabetween")
	public ReponseStructure<List<Student>> fetchByAgeBetween(@RequestParam("start") int  start,@RequestParam("end") int  end)
	{
		List<Student> students=sr.findByAgeBetween(start,end);
		ReponseStructure<List<Student>> rs=new ReponseStructure<List<Student>>();
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setData(students);
		rs.setMessage("data found");
		return rs;	
	}
	@GetMapping("/fetchgreaterdata")
	public ReponseStructure<List<Student>> fetchByAgeGreaterThan(@RequestParam("age") int  age)
	{
		List<Student> students=sr.findByAgeGreaterThan(age);
		ReponseStructure<List<Student>> rs=new ReponseStructure<List<Student>>();
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setData(students);
		rs.setMessage("data found");
		return rs;	
	}
	@GetMapping("/fetchlesserdata")
	public ReponseStructure<List<Student>> fetchByAgeSmaller(@RequestParam("age") int  age)
	{
		List<Student> students=sr.findByAgeLessThan(age);
		ReponseStructure<List<Student>> rs=new ReponseStructure<List<Student>>();
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setData(students);
		rs.setMessage("data found");
		return rs;	
	}
}



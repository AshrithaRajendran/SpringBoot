package com.jso.springbootdemo;

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

import com.jsp.springbootdemo.entities.Product;

import com.jsp.springbootdemo.repository.ProductRepository;

@RestController
public class ProductController{
	@Autowired
	ProductRepository pr;
	
	@PostMapping("/saveproduct")   
	public  ReponseStructure<Product> saveproduct(@RequestBody Product product)
	{
		pr.save(product);
		ReponseStructure<Product> rs=new ReponseStructure<Product>();
		rs.setData(product);
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setMessage("data inserted successfully");
		return rs;
	}
	
	@GetMapping("/fetchdatabyid")
	public ReponseStructure<Product> fetchDataById(@RequestParam("id") int id)
	{
		try
		{
			Optional<Product> option=pr.findById(id);
		Product p=option.get();
			ReponseStructure<Product> rs=new ReponseStructure<Product>();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(p);
			rs.setMessage("data found");
			return rs;		
		}
	catch(NoSuchElementException ns)
	{
		ReponseStructure<Product> rs=new ReponseStructure<Product>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setData(null);
		rs.setMessage("data not found");
		return rs;	
	}
	
	}	
	
	@PutMapping("/updateProductdata")
	public  ReponseStructure<Product> updateProduct(@RequestBody Product product)
	{
		pr.save(product);
		try {
		ReponseStructure<Product> rs=new ReponseStructure<Product>();
		rs.setStatuscode(HttpStatus.ACCEPTED.value());
		rs.setMessage("data updated");
		rs.setData(product);
		return rs;
		} catch (Exception e)
		{
			ReponseStructure<Product> rs=new ReponseStructure<Product>();
			rs.setStatuscode(HttpStatus.NOT_ACCEPTABLE.value());
			rs.setMessage("data not updated");
			rs.setData(null);
			return rs;
		}
	}
	
	@DeleteMapping("/deleteproductbyid")
	public ReponseStructure<Product> deleteProductById(@RequestParam("id") int id)
	{
		pr.deleteById(id);
		ReponseStructure<Product> rs=new ReponseStructure<>();
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("data delete successfully");
		return rs;
			}
	
	
	
	}  




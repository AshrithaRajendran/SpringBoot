package com.jsp.springbootdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.springbootdemo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	void deleteProductByBrand(String brand);
	void deleteProductByCost(Double cost);
	List<Product> findByBrand(String brand);
	List<Product> findByCategory(String category);

}

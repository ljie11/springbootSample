package com.evolocity.springboot.service;


import com.evolocity.springboot.model.Product;

import java.util.List;
/**
 * An interface for basic product actions
 * @author Jie
 *
 */
public interface ProductService {
	
	Product findById(Long id);

	Product findByName(String name);

	void saveProduct(Product product);

	void updateProduct(Product product);

	void deleteproductById(Long id);

	List<Product> findAllProducts();

	boolean isProductExist(Product product);
}
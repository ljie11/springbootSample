package com.evolocity.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.evolocity.springboot.model.Product;
import com.evolocity.springboot.service.ProductService;
import com.evolocity.springboot.util.CustomizedException;

/**
 *  A simple controller for product create, list , update and remove.
 *  
 * @author Jie
 *
 */
@RestController
@RequestMapping("/api")
public class ProductResource {

	public static final Logger logger = LoggerFactory.getLogger(ProductResource.class);

	@Autowired
	ProductService productService; 

	
	/**
	 *  Get all products
	 * @return
	 */
	@RequestMapping(value = "/product/", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> listAllProducts() {
		
		List<Product> products = productService.findAllProducts();
		
		if (products.isEmpty()) {
			
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	/**
	 *  Get a product by id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getproduct(@PathVariable("id") long id) {
		
		logger.info("Fetching product with id {}", id);
		
		Product product = productService.findById(id);
		
		if (null == product) {
			
			logger.error("Product with id {} not found.", id);
			
			return new ResponseEntity(new CustomizedException("Product with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}


	/**
	 *  Add a new product
	 * @param product
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(value = "/product/", method = RequestMethod.POST)
	public ResponseEntity<?> addProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		
		logger.info("Creating product : {}", product);

		if (productService.isProductExist(product)) {
			
			logger.error("Unable to create. A Product with name {} already exist", product.getName());
			return new ResponseEntity(new CustomizedException("Unable to create. A Product with name " + product.getName() + " already exist."), HttpStatus.CONFLICT);
		}
		
		productService.saveProduct(product);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/product/{id}").buildAndExpand(product.getId()).toUri());
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}


	/**
	 * Update an existing product
	 * @param id
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		
		logger.info("Updating Product with id {}", id);

		Product currentProduct = productService.findById(id);

		if (null == currentProduct) {
			
			logger.error("Unable to update. Product with id {} not found.", id);
			return new ResponseEntity(new CustomizedException("Unable to upate product.The Product with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
			
		}

		currentProduct.setName(product.getName());
		currentProduct.setQuantity(product.getQuantity());

		productService.updateProduct(currentProduct);
		return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
	}


	/**
	 * Remove an existing product
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
		
		logger.info("Fetching & Deleting Product with id {}", id);

		Product product = productService.findById(id);
		
		if (product == null) {
			
			logger.error("Unable to delete. Product with id {} not found.", id);
			return new ResponseEntity(new CustomizedException("Unable to delete. Product with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
			
		}
		
		productService.deleteproductById(id);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}
}
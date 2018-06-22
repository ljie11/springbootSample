package com.evolocity.springboot.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.evolocity.springboot.model.Product;
import com.evolocity.springboot.repositories.ProductRepository;


/**
 * Product service test
 * @author Jie
 *
 */

@RunWith(SpringRunner.class)
public class ProductServiceImplTest {
 
    @TestConfiguration
    static class ProductServiceImplTestContextConfiguration {
  
        @Bean
        public ProductService productService() {
            return new ProductServiceImpl();
        }
    }
 
    @Autowired
    private ProductService prodService;
 
    @MockBean
    private ProductRepository prodRepository;
    
    @Before
    public void setUp() {
    	
        String name = "phone";
        Long id = (long) 2;
        
    	Product prod = new Product();
    	prod.setId(id);
        prod.setName(name);
        prod.setQuantity(2);
     
        Mockito.when(prodRepository.findOne(prod.getId()))
          .thenReturn(prod);
        
        Mockito.when(prodRepository.findByName(prod.getName()))
        .thenReturn(prod);
    }
    
    @Test
    public void whenValidId_thenProductShouldBeFound() {
        
        Product found = prodService.findById((long) 2);
        assertEquals(found.getName(), "phone");
     }

    
    @Test
    public void whenValidName_thenProductShouldBeFound() {
        String name = "phone";
        
        Product found = prodService.findByName(name);
        assertEquals(found.getName(), name);
     }
    
    @Test
    public void whenValidProduct_thenProductShouldBeExisted() {
        String name = "phone";

    	Product prod = new Product();
        prod.setName(name);
        prod.setQuantity(2);
     
        assertEquals(prodService.isProductExist(prod), true);
        
     }

}
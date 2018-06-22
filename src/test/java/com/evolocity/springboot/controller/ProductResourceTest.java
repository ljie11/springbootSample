package com.evolocity.springboot.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.evolocity.springboot.model.Product;
import com.evolocity.springboot.service.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ProductResource.class)
public class ProductResourceTest {
 
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private ProductService prodservice;
    
    
    @Test
    public void givenProducts_whenGetProducts_thenReturnList()
      throws Exception {
         
        Product prod = new Product();
     
        List<Product> allProds = Arrays.asList(prod);
        Mockito.when(prodservice.findAllProducts()).thenReturn(allProds);
        
        //to do complete the test with list check
        mvc.perform(get("/api/product/")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());

    }
    
    /**
     *  add unit test to the add, update, get  and remove
     */
 
}
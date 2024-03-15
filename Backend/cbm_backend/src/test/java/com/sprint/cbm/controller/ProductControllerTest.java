package com.sprint.cbm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sprint.cbm.entities.Product;
import com.sprint.cbm.entities.ProductLine;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.services.ProductServices;

public class ProductControllerTest {

    @Mock
    private ProductServices productServices;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        // Mock data
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(productServices.getAllProducts()).thenReturn(productList);

        // Perform the GET request
        List<Product> result = productController.getAll();

        // Verify the result
        assertEquals(productList, result);
        verify(productServices, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductByNameWithInvalidName() {
        // Mock data
        String productName = "NonExistentProduct";
        when(productServices.getByName(productName)).thenReturn(null);

        // Perform the GET request and verify the exception
        assertThrows(NotFoundException.class, () -> {
            productController.getByName(productName);
        });

        verify(productServices, times(1)).getByName(productName);
    }

    @Test
    public void testCreateProductLine() {
        // Mock data
        Product product = new Product();

        // Perform the POST request
        ResponseEntity<ProductLine> result = productController.createProductLine(product);

        // Verify the result
        assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
        verify(productServices, times(1)).createProduct(product);
    }

}


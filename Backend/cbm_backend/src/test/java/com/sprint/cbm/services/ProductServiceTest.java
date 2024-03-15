package com.sprint.cbm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sprint.cbm.entities.Product;
import com.sprint.cbm.repository.ProductRepo;

public class ProductServiceTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductServices productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByName_ExistingName_ReturnsProduct() {
        // Arrange
        String name = "1952 Alpine Renault 1300";
        Product expectedProduct = new Product();

        when(productRepo.findByName(name)).thenReturn(expectedProduct);

        // Act
        Product result = productService.getByName(name);

        // Assert
        assertEquals(expectedProduct, result);
        verify(productRepo).findByName(name);
    }

    @Test
    void testGetById_ExistingId_ReturnsProduct() {
        // Arrange
        String id = "10102";
        Optional<Product> expectedProduct = Optional.of(new Product());

        when(productRepo.findByCode(id)).thenReturn(expectedProduct);

        // Act
        Product result = productService.getById(id);

        // Assert
        assertEquals(expectedProduct, result);
        verify(productRepo).findByCode(id);
    }
    
    @Test
    void testFindByProductName_ExistingProductName_ReturnsProduct() {
        // Arrange
        String productName = "1952 Alpine Renault 1300";
        Product expectedProduct = new Product();

        when(productRepo.findByName(productName)).thenReturn(expectedProduct);

        // Act
        Product result = productService.findByProductName(productName);

        // Assert
        assertEquals(expectedProduct, result);
        verify(productRepo).findByName(productName);
    }

    @Test
    void testFindByProductScale_ExistingProductScale_ReturnsListOfProducts() {
        // Arrange
        String productScale = "1:10";
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        when(productRepo.findByProductScale(productScale)).thenReturn(products);

        // Act
        List<Product> result = productService.findByProductScale(productScale);

        // Assert
        assertEquals(products, result);
        verify(productRepo).findByProductScale(productScale);
    }

    @Test
    void testCreateProduct_ValidProduct_ReturnsCreatedProduct() {
        // Arrange
        Product product = new Product();
        when(productRepo.save(product)).thenReturn(product);

        // Act
        Product createdProduct = productService.createProduct(product);

        // Assert
        assertEquals(product, createdProduct);
        verify(productRepo).save(product);
    }
}


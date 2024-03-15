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

import com.sprint.cbm.entities.ProductLine;
import com.sprint.cbm.exception.InvalidException;
import com.sprint.cbm.services.ProductLineServices;

public class ProductLineControllerTest {

    @Mock
    private ProductLineServices productLineServices;

    @InjectMocks
    private ProductLineController productLineController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProductLine() {
        // Mock data
        List<ProductLine> productLines = Arrays.asList(new ProductLine(), new ProductLine());
        when(productLineServices.getAll()).thenReturn(productLines);

        // Perform the GET request
        List<ProductLine> result = productLineController.getAllProductLine();

        // Verify the result
        assertEquals(productLines, result);
        verify(productLineServices, times(1)).getAll();
    }

    @Test
    public void testGetProductLineByNameWithValidName() {
        // Mock data
        String productLineName = "TestProductLine";
        ProductLine productLine = new ProductLine();
        when(productLineServices.getById(productLineName)).thenReturn(productLine);

        // Perform the GET request
        ProductLine result = productLineController.getByName(productLineName);

        // Verify the result
        assertEquals(productLine, result);
        verify(productLineServices, times(1)).getById(productLineName);
    }

    @Test
    public void testUpdateProductLineDescriptionWithValidName() {
        // Mock data
        String productLineName = "TestProductLine";
        String description = "Updated description";
        ProductLine productLine = new ProductLine();
        when(productLineServices.getById(productLineName)).thenReturn(productLine);

        // Perform the PUT request
        ResponseEntity<ProductLine> result = productLineController.updateDescription(productLineName, description);

        // Verify the result
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(productLineServices, times(1)).updateProductLine(productLineName, description);
    }
    
    
    @Test
    public void testCreateProductLineWithInvalidProductLine() {
        // Mock data
        ProductLine productLine = null;

        // Perform the POST request and verify the exception
        assertThrows(InvalidException.class, () -> {
            productLineController.createProductLine(productLine);
        });

        verify(productLineServices, times(0)).createProductLine(productLine);
    }

}


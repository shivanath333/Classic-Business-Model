package com.sprint.cbm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sprint.cbm.entities.OrderDetails;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.repository.OrderDetailsRepo;

public class OrderDetailsServiceTest {

    @Mock
    private OrderDetailsRepo orderDetailsRepo;

    @InjectMocks
    private OrderDetailsServices orderDetailsService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderDetailsCount_QuantityAttribute_ReturnsSumQuantityOrdered() {
        // Arrange
        String attribute = "quantity";
        int expectedCount = 10;

        when(orderDetailsRepo.sumQuantityOrdered()).thenReturn(expectedCount);

        // Act
        int actualCount = orderDetailsService.getOrderDetailsCount(attribute);

        // Assert
        assertEquals(expectedCount, actualCount);
        verify(orderDetailsRepo).sumQuantityOrdered();
    }

    @Test
    void testGetOrderDetailsCount_PriceAttribute_ReturnsCountByPriceEachNotNull() {
        // Arrange
        String attribute = "price";
        int expectedCount = 5;

        when(orderDetailsRepo.countByPriceEachNotNull()).thenReturn(expectedCount);

        // Act
        int actualCount = orderDetailsService.getOrderDetailsCount(attribute);

        // Assert
        assertEquals(expectedCount, actualCount);
        verify(orderDetailsRepo).countByPriceEachNotNull();
    }

    @Test
    void testGetOrderDetailsCount_OrderNumberAttribute_ReturnsCountByOrderLineNumberNotNull() {
        // Arrange
        String attribute = "order number";
        int expectedCount = 3;

        when(orderDetailsRepo.countByOrderLineNumberNotNull()).thenReturn(expectedCount);

        // Act
        int actualCount = orderDetailsService.getOrderDetailsCount(attribute);

        // Assert
        assertEquals(expectedCount, actualCount);
        verify(orderDetailsRepo).countByOrderLineNumberNotNull();
    }

    @Test
    void testGetOrderDetailsCount_InvalidAttribute_ThrowsNotFoundException() {
        // Arrange
        String attribute = "invalid";

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            orderDetailsService.getOrderDetailsCount(attribute);
        });
    }

    @Test
    void testGetByOrderNumber_ExistingOrderNumber_ReturnsListOfOrderDetails() {
        // Arrange
        int orderNumber = 1234;
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(new OrderDetails());
        orderDetailsList.add(new OrderDetails());

        when(orderDetailsRepo.findByOrderNumber(orderNumber)).thenReturn(orderDetailsList);

        // Act
        List<OrderDetails> result = orderDetailsService.getByOrderNumber(orderNumber);

        // Assert
        assertEquals(orderDetailsList, result);
        verify(orderDetailsRepo).findByOrderNumber(orderNumber);
    }

    @Test
    void testGetAmountByOrderNumber_ExistingOrderNumber_ReturnsListOfAmounts() {
        // Arrange
        int orderNumber = 1234;
        List<Double> amountsList = Arrays.asList(10.5, 20.3, 15.8);

        when(orderDetailsRepo.findAmountByOrderNumber(orderNumber)).thenReturn(amountsList);

        // Act
        List<Double> result = orderDetailsService.getAmountByOrderNumber(orderNumber);

        // Assert
        assertEquals(amountsList, result);
        verify(orderDetailsRepo).findAmountByOrderNumber(orderNumber);
    }

    @Test
    void testGetByTotalSale_ReturnsTotalSale() {
        // Arrange
        int expectedTotalSale = 10000;

        when(orderDetailsRepo.findByTotalSale()).thenReturn(expectedTotalSale);

        // Act
        int actualTotalSale = orderDetailsService.getByTotalSale();

        // Assert
        assertEquals(expectedTotalSale, actualTotalSale);
        verify(orderDetailsRepo).findByTotalSale();
    }

    @Test
    void testAdd_ValidOrderDetails_ReturnsAddedOrderDetails() {
        // Arrange
        OrderDetails orderDetails = new OrderDetails();
        when(orderDetailsRepo.save(orderDetails)).thenReturn(orderDetails);

        // Act
        OrderDetails addedOrderDetails = orderDetailsService.add(orderDetails);

        // Assert
        assertEquals(orderDetails, addedOrderDetails);
        verify(orderDetailsRepo).save(orderDetails);
    }
}


package com.sprint.cbm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sprint.cbm.entities.Order;
import com.sprint.cbm.repository.OrderRepo;

public class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;

    @InjectMocks
    private OrderServices orderService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderByNumber_ExistingNumber_ReturnsOrder() {
        // Arrange
        int number = 10100;
        Order expectedOrder = new Order();

        when(orderRepo.findById(number)).thenReturn(expectedOrder);

        // Act
        Order result = orderService.getOrderByNumber(number);

        // Assert
        assertEquals(expectedOrder, result);
        verify(orderRepo).findById(number);
    }

    @Test
    void testGetOrderByNumber_NonExistingNumber_ReturnsNull() {
        // Arrange
        int number = 1;

        when(orderRepo.findById(number)).thenReturn(new Order());

        // Act
        Order result = orderService.getOrderByNumber(1);
        System.out.println(result);

        // Assert
        assertNotNull(result);
        verify(orderRepo).findById(number);
    }

    @Test
    void testGetOrderByDate_ExistingDate_ReturnsListOfOrders() {
    	
        // Arrange
        Date date = new Date(2003-01-06);
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());

        when(orderRepo.findByOrderDate(date)).thenReturn(orders);

        // Act
        List<Order> result = orderService.getOrderByDate(date);

        // Assert
        assertEquals(orders, result);
        verify(orderRepo).findByOrderDate(date);
    }

    @Test
    void testGetByCustomerNumber_ExistingCustomerNumber_ReturnsListOfOrders() {
        // Arrange
        int customerNumber = 123;
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());

        when(orderRepo.findByCustomerNumber(customerNumber)).thenReturn(orders);

        // Act
        List<Order> result = orderService.getByCustomerNumber(customerNumber);

        // Assert
        assertEquals(orders, result);
        verify(orderRepo).findByCustomerNumber(customerNumber);
    }

    @Test
    void testGetByStatusAndCustNumber_ExistingCustomerNumberAndStatus_ReturnsListOfOrders() {
        // Arrange
        int customerNumber = 123;
        String status = "Complete";
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());

        when(orderRepo.findByStatusAndCustNumber(customerNumber, status)).thenReturn(orders);

        // Act
        List<Order> result = orderService.getByStatusAndCustNumber(customerNumber, status);

        // Assert
        assertEquals(orders, result);
        verify(orderRepo).findByStatusAndCustNumber(customerNumber, status);
    }

    @Test
    void testCreateList_ValidOrderList_ReturnsSavedOrderList() {
    	
        // Arrange
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        when(orderRepo.saveAll(orders)).thenReturn(orders);

        // Act
        List<Order> result = orderService.createList(orders);

        // Assert
        assertEquals(orders, result);
        verify(orderRepo).saveAll(orders);
    }
}


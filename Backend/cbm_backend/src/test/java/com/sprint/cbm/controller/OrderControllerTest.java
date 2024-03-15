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

import com.sprint.cbm.entities.Order;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.services.OrderServices;

public class OrderControllerTest {

    @Mock
    private OrderServices orderServices;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        // Mock data
        List<Order> orders = Arrays.asList(new Order(), new Order());
        when(orderServices.getAll()).thenReturn(orders);

        // Perform the GET request
        List<Order> result = orderController.getAll();

        // Verify the result
        assertEquals(orders, result);
        verify(orderServices, times(1)).getAll();
    }

    @Test
    public void testGetNonExistingOrderByNumber() {
        // Mock data
        int orderNumber = 1;
        when(orderServices.getOrderByNumber(orderNumber)).thenReturn(null);

        // Perform the GET request and verify the exception
        assertThrows(NotFoundException.class, () -> {
            orderController.getOrdersByNumber(orderNumber);
        });

        verify(orderServices, times(1)).getOrderByNumber(orderNumber);
    }

    // Add more test cases for other methods in the OrderController class...

    @Test
    public void testAddOrder() {
        // Mock data
        Order order = new Order();

        // Mock the service method
        when(orderServices.createOrder(order)).thenReturn(order);

        // Perform the POST request
        Order result = orderController.add(order);

        // Verify the result
        assertEquals(order, result);
        verify(orderServices, times(1)).createOrder(order);
    }

   
}

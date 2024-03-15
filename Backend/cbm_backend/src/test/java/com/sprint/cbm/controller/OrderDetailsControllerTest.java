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

import com.sprint.cbm.entities.OrderDetails;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.services.OrderDetailsServices;

public class OrderDetailsControllerTest {

    @Mock
    private OrderDetailsServices orderDetailsServices;

    @InjectMocks
    private OrderDetailsController orderDetailsController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrderDetails() {
        // Mock data
        List<OrderDetails> orderDetailsList = Arrays.asList(new OrderDetails(), new OrderDetails());
        when(orderDetailsServices.getAll()).thenReturn(orderDetailsList);

        // Perform the GET request
        List<OrderDetails> result = orderDetailsController.getAll();

        // Verify the result
        assertEquals(orderDetailsList, result);
        verify(orderDetailsServices, times(1)).getAll();
    }

    @Test
    public void testGetOrderDetailsCountWithValidAttribute() {
        // Mock data
        String attribute = "quantity";
        int count = 5;
        when(orderDetailsServices.getOrderDetailsCount(attribute)).thenReturn(count);

        // Perform the GET request
        int result = orderDetailsController.getOrderDetailsCount(attribute);

        // Verify the result
        assertEquals(count, result);
        verify(orderDetailsServices, times(1)).getOrderDetailsCount(attribute);
    }

    @Test
    public void testGetOrderDetailsCountWithInvalidAttribute() {
        // Mock data
        String attribute = "invalidAttribute";
        int count = 0;
        when(orderDetailsServices.getOrderDetailsCount(attribute)).thenReturn(count);

        // Perform the GET request and verify the exception
        assertThrows(NotFoundException.class, () -> {
            orderDetailsController.getOrderDetailsCount(attribute);
        });

        verify(orderDetailsServices, times(1)).getOrderDetailsCount(attribute);
    }


    @Test
    public void testAddOrderDetails() {
        // Mock data
        OrderDetails orderDetails = new OrderDetails();

        // Mock the service method
        when(orderDetailsServices.add(orderDetails)).thenReturn(orderDetails);

        // Perform the POST request
        OrderDetails result = orderDetailsController.add(orderDetails);

        // Verify the result
        assertEquals(orderDetails, result);
        verify(orderDetailsServices, times(1)).add(orderDetails);
    }

}


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

import com.sprint.cbm.entities.Payments;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.services.PaymentServices;

public class PaymentControllerTest {

    @Mock
    private PaymentServices paymentServices;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPayments() {
        // Mock data
        List<Payments> paymentsList = Arrays.asList(new Payments(), new Payments());
        when(paymentServices.getAll()).thenReturn(paymentsList);

        // Perform the GET request
        List<Payments> result = paymentController.getAll();

        // Verify the result
        assertEquals(paymentsList, result);
        verify(paymentServices, times(1)).getAll();
    }
    
    @Test
    public void testGetPaymentDetailsOfCustomerWithInvalidId() {
        // Mock data
        int customerId = 1;
        when(paymentServices.paymentDetailsOfCustomer(customerId)).thenReturn(Arrays.asList());

        // Perform the GET request and verify the exception
        assertThrows(NotFoundException.class, () -> {
            paymentController.paymentDetailsOf(customerId);
        });

        verify(paymentServices, times(1)).paymentDetailsOfCustomer(customerId);
    }


    @Test
    public void testAddPayment() {
        // Mock data
        Payments payment = new Payments();

        // Mock the service method
        when(paymentServices.create(payment)).thenReturn(payment);

        // Perform the POST request
        Payments result = paymentController.addPayment(payment);

        // Verify the result
        assertEquals(payment, result);
        verify(paymentServices, times(1)).create(payment);
    }

}


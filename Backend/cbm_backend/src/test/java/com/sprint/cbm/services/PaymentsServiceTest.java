package com.sprint.cbm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.entities.Payments;
import com.sprint.cbm.repository.PaymentRepo;

public class PaymentsServiceTest {

    @Mock
    private PaymentRepo paymentRepo;

    @InjectMocks
    private PaymentServices paymentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPaymentDetailsOfCustomer() {
        // Arrange
        int customerId = 1;
        List<Payments> paymentsList = new ArrayList<>();
        paymentsList.add(new Payments());
        paymentsList.add(new Payments());

        when(paymentRepo.findByCustomerId(customerId)).thenReturn(paymentsList);

        // Act
        List<Payments> result = paymentService.paymentDetailsOfCustomer(customerId);

        // Assert
        assertEquals(paymentsList, result);
        verify(paymentRepo).findByCustomerId(customerId);
    }
    

    @Test
    void testGetByMaxAmount() {
        // Arrange
        Customer expectedCustomer = new Customer();

        when(paymentRepo.customerWithMaxAmount()).thenReturn(expectedCustomer);

        // Act
        Customer result = paymentService.getByMaxAmount();

        // Assert
        assertEquals(expectedCustomer, result);
        verify(paymentRepo).customerWithMaxAmount();
    }

    @Test
    void testGetCustomerByPayment() {
        // Arrange
        Date startDate = new Date(2004-07-28);
        Date endDate = new Date(2005-03-10);
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer());
        expectedCustomers.add(new Customer());

        when(paymentRepo.paymentDoneByCustomersInDateBetween(startDate, endDate)).thenReturn(expectedCustomers);

        // Act
        List<Customer> result = paymentService.getCustomerByPayment(startDate, endDate);

        // Assert
        assertEquals(expectedCustomers, result);
        verify(paymentRepo).paymentDoneByCustomersInDateBetween(startDate, endDate);
    }

    @Test
    void testCreate() {
        // Arrange
        Payments payment = new Payments();
        when(paymentRepo.save(payment)).thenReturn(payment);

        // Act
        Payments createdPayment = paymentService.create(payment);

        // Assert
        assertEquals(payment, createdPayment);
        verify(paymentRepo).save(payment);
    }

}


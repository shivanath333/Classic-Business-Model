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

import com.sprint.cbm.entities.Office;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.repository.OfficeRepo;
import com.sprint.cbm.services.OfficeServices;

public class OfficeControllerTest {

    @Mock
    private OfficeServices officeServices;

    @Mock
    private OfficeRepo officeRepo;

    @InjectMocks
    private OfficeController officeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOffices() {
        // Mock data
        List<Office> offices = Arrays.asList(new Office(), new Office());
        when(officeServices.getAll()).thenReturn(offices);

        // Perform the GET request
        List<Office> result = officeController.getAll();

        // Verify the result
        assertEquals(offices, result);
        verify(officeServices, times(1)).getAll();
    }

    @Test
    public void testGetNonExistingOfficeByCode() {
        // Mock data
        int officeCode = 1;
        when(officeServices.getById(officeCode)).thenReturn(null);

        // Perform the GET request and verify the exception
        assertThrows(NotFoundException.class, () -> {
            officeController.getByCode(officeCode);
        });

        verify(officeServices, times(1)).getById(officeCode);
    }

    @Test
    public void testSaveOffice() {
        // Mock data
        Office office = new Office();

        // Perform the POST request
        ResponseEntity<Office> response = officeController.saveOffice(office);

        // Verify the response status
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(officeRepo, times(1)).save(office);
    }

    @Test
    public void testUpdateOffice() {
        // Mock data
        int officeCode = 1;
        String phoneNumber = "1234567890";
        Office office = new Office();
        office.setCode(officeCode);
        when(officeServices.updatePhone(officeCode, phoneNumber)).thenReturn(office);

        // Perform the PUT request
        Office result = officeController.updateOffice(officeCode, phoneNumber);

        // Verify the result
        assertEquals(office, result);
        verify(officeServices, times(1)).updatePhone(officeCode, phoneNumber);
    }

    @Test
    public void testUpdateNonExistingOffice() {
        // Mock data
        int officeCode = 1;
        String phoneNumber = "1234567890";
        when(officeServices.updatePhone(officeCode, phoneNumber)).thenReturn(null);

        // Perform the PUT request and verify the exception
        assertThrows(NotFoundException.class, () -> {
            officeController.updateOffice(officeCode, phoneNumber);
        });

        verify(officeServices, times(1)).updatePhone(officeCode, phoneNumber);
    }


}

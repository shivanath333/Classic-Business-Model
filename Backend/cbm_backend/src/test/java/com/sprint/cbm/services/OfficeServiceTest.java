package com.sprint.cbm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.entities.Office;
import com.sprint.cbm.repository.OfficeRepo;

@SpringBootTest
public class OfficeServiceTest {

	@Mock
	private OfficeRepo officeRepo;

	@InjectMocks
	private OfficeServices officeServices;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetById_ExistingId_ReturnsOffice() {
		// Arrange
		int officeId = 1;
		Office expectedOffice = new Office();
		expectedOffice.setCode(officeId);

		when(officeRepo.findByCode(officeId)).thenReturn(expectedOffice);

		// Act
		Office actualOffice = officeServices.getById(officeId);

		// Assert
		assertEquals(expectedOffice, actualOffice);
		verify(officeRepo).findByCode(officeId);
	}

	@Test
	void testGetOfficeByCities_ExistingCities_ReturnsListOfOffices() {
		// Arrange
		List<String> cities = Arrays.asList("New York", "Los Angeles");
		List<Office> officesList = new ArrayList<>();
		officesList.add(new Office());
		officesList.add(new Office());

		when(officeRepo.findByCityIn(cities)).thenReturn(officesList);

		// Act
		List<Office> result = officeServices.getOfficeByCities(cities);

		// Assert
		assertEquals(officesList, result);
		verify(officeRepo).findByCityIn(cities);
	}

	@Test
	void testGetCustomersByOfficeCode_ExistingCode_ReturnsListOfCustomers() {
		// Arrange
		int officeCode = 1234;
		List<Customer> customersList = new ArrayList<>();
		customersList.add(new Customer());
		customersList.add(new Customer());

		when(officeRepo.getCustomersByOfficeCode(officeCode)).thenReturn(customersList);

		// Act
		List<Customer> result = officeServices.getCustomersByOfficeCode(officeCode);

		// Assert
		assertEquals(customersList, result);
		verify(officeRepo).getCustomersByOfficeCode(officeCode);
	}

	@Test
	void testUpdatePhone_ExistingCode_ReturnsUpdatedOffice() {
		// Arrange
		int officeCode = 1234;
		String newPhone = "1234567890";

		Office office = new Office();
		office.setCode(officeCode);

		when(officeRepo.findByCode(officeCode)).thenReturn(office);
		when(officeRepo.save(office)).thenReturn(office);

		// Act
		Office updatedOffice = officeServices.updatePhone(officeCode, newPhone);

		// Assert
		assertEquals(newPhone, updatedOffice.getPhone());
		verify(officeRepo).findByCode(officeCode);
		verify(officeRepo).save(office);
	}

	@Test
	void testUpdateAddress_ExistingCode_ReturnsUpdatedOffice() {
		// Arrange
		int officeCode = 1234;
		String newAddress = "123 Main St";

		Office office = new Office();
		office.setCode(officeCode);

		when(officeRepo.findByCode(officeCode)).thenReturn(office);
		when(officeRepo.save(office)).thenReturn(office);

		// Act
		Office updatedOffice = officeServices.updateAddress(officeCode, newAddress);

		// Assert
		assertEquals(newAddress, updatedOffice.getAddressLine1());
		verify(officeRepo).findByCode(officeCode);
		verify(officeRepo).save(office);
	}

	@Test
	void testUpdateDetails_ExistingId_ReturnsUpdatedOffice() {
		// Arrange
		int officeId = 1;
		Office existingOffice = new Office();
		existingOffice.setCode(officeId);

		Office updatedOffice = new Office();
		updatedOffice.setCode(officeId);
		updatedOffice.setCity("New City");
		updatedOffice.setPhone("1234567890");
		System.out.println(updatedOffice);

		when(officeRepo.findById(officeId)).thenReturn(Optional.of(existingOffice));
		when(officeRepo.save(existingOffice)).thenReturn(updatedOffice);

		// Act
		Office result = officeServices.updateDetails(officeId, updatedOffice);
		System.out.println(result);

		// Assert
		assertEquals(updatedOffice, result);
		verify(officeRepo).findById(officeId);
		verify(officeRepo).save(existingOffice);
	}
}

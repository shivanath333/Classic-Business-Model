package com.sprint.cbm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.entities.Office;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.repository.OfficeRepo;
import com.sprint.cbm.services.OfficeServices;

import io.swagger.v3.oas.annotations.Operation;

/**
 * Controller class for handling Office-related endpoints.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/office")
public class OfficeController {

	@Autowired

	private OfficeServices officeServices;

	@Autowired
	private OfficeRepo officeRepo;

	/**
	 * Retrieves all offices.
	 *
	 * @return List of all offices
	 */
	@GetMapping("")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

	public List<Office> getAll() {
		return officeServices.getAll();
	}

	/**
	 * Retrieves an office by its code.
	 *
	 * @param code The code of the office to retrieve
	 * @return The office with the specified code
	 * @throws NotFoundException if the office is not found
	 */

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{code}")

	public Office getByCode(@PathVariable int code) {
		if (officeServices.getById(code) != null) {
			return officeServices.getById(code);
		} else {
			throw new NotFoundException("Office not found");
		}
	}

	/**
	 * Retrieves a list of offices based on city names.
	 *
	 * @param cities List of city names to search for
	 * @return List of offices in the specified cities
	 * @throws NotFoundException if no offices are found
	 */

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("city_names/{cities}")
	public List<Office> getofList(@PathVariable List<String> cities) {
		if (officeServices.getOfficeByCities(cities).size() > 0) {
			return officeServices.getOfficeByCities(cities);
		} else {
			throw new NotFoundException("Offices not found");
		}
	}

	/**
	 * Retrieves customers associated with an office.
	 *
	 * @param office_code The code of the office
	 * @return List of customers associated with the specified office
	 * @throws NotFoundException if no customers are found or the office code is
	 *                           invalid
	 */

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{office_code}/customer/")

	public List<Customer> getCustomerByOffice(@PathVariable int office_code) {
		if (officeServices.getCustomersByOfficeCode(office_code).size() > 0) {
			return officeServices.getCustomersByOfficeCode(office_code);
		} else {
			throw new NotFoundException("customer not found,check office code");
		}

	}
	
	 /**
     * Saves a new office.
     *
     * @param office The office to save
     * @return ResponseEntity indicating the status of the operation
     */

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/newoffice", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Office> saveOffice(@RequestBody Office office) {
		try {
			Office officeres = officeRepo.save(office);
			return ResponseEntity.status(HttpStatus.CREATED).build();

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	 /**
     * Updates the contact number of an office.
     *
     * @param code   The code of the office to update
     * @param number The new contact number
     * @return The updated office
     * @throws NotFoundException if the office is not found
     */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/contact/{code}/{number}")
	public Office updateOffice(@PathVariable int code, @RequestBody String number) {
		Office office = officeServices.updatePhone(code, number);
		if (office != null) {
			return office;
		} else {
			// Return appropriate response when the Office entity is not found
			throw new NotFoundException("Office not found for code: " + code);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/address/{code}/{address}")
	public Office updateAddress(@PathVariable int code, @RequestBody String address) {
		Office office = officeServices.updateAddress(code, address);
		if (office != null) {
			return office;
		} else {
			// Return appropriate response when the Office entity is not found
			throw new NotFoundException("Office not found for code: " + code);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	@Operation(summary = "Update Office based on OfficeCode")
	public ResponseEntity<Office> update(@PathVariable int id, @RequestBody Office office) {
		Office officeNew = officeServices.getById(id);
		if (officeNew != null) {
			officeServices.updateDetails(id, office);
			return ResponseEntity.ok(office);
		} else {
			throw new NotFoundException("Order not found");
		}
	}

}

package com.sprint.cbm.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.entities.Office;
import com.sprint.cbm.repository.OfficeRepo;

@Service
public class OfficeServices {
	
	@Autowired
	
	private OfficeRepo officeRepo;
	
	
	//to get all the data from the office table
	public List<Office> getAll(){
		return officeRepo.findAll();
	}
	
	
	//to get  office details by id
	public Office getById(int id) {
		
		return officeRepo.findByCode(id);
		
	}
	
	//using number of cities
	
	public List<Office> getOfficeByCities(List<String> cities){
		return officeRepo.findByCityIn(cities);
		
	}
	
	public List<Customer> getCustomersByOfficeCode(int code){
		return officeRepo.getCustomersByOfficeCode(code);
		
	}
	
	public Office updatePhone(int code, String phone) {
	    Office office = officeRepo.findByCode(code);
	    if (office != null) {
	        office.setPhone(phone);
	        officeRepo.save(office);
	    }
	    return office;
	}
	

	public Office updateAddress(int code, String address) {
	    Office office = officeRepo.findByCode(code);
	    if (office != null) {
	        office.setAddressLine1(address);
	        officeRepo.save(office);
	    }
	    return office;
	}
	
	public Office updateDetails(int id, Office office) {

        Office office2 = officeRepo.findById(id).orElse(null);

        office2.setCity(office.getCity());
        office2.setPhone(office.getPhone());
        office2.setAddressLine1(office.getAddressLine1());
        office2.setAddressLine2(office.getAddressLine2());
        office2.setState(office.getState());
        office2.setCountry(office.getCountry());
        office2.setPostalCode(office.getPostalCode());
        office2.setTerritory(office.getTerritory());

        return officeRepo.save(office2);

    }
  
	

	


}

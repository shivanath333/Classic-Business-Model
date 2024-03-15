package com.sprint.cbm.controller;

import java.util.List;
import java.util.Map;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.cbm.entities.OrderDetails;
import com.sprint.cbm.entities.Product;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.services.OrderDetailsServices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/order_details")
public class OrderDetailsController {
	
	@Autowired 
	
	private OrderDetailsServices orderDetailsServices;
	
	@GetMapping("")
	
	@PreAuthorize("hasRole('ADMIN')")
	
	public List<OrderDetails> getAll(){
		return orderDetailsServices.getAll();
	}
	
	@GetMapping("/count/{attribute}")
	public int getOrderDetailsCount(@PathVariable("attribute") String attribute) {
	    int count = orderDetailsServices.getOrderDetailsCount(attribute);
	    if(count != 0) {
	    return count;
	    }
	    else {
	    	 throw new NotFoundException("No details found for given attribute : "+ attribute+ ", check the attribute select valid one");
	    }
	}
	
	
	@GetMapping("/{order_number}")
    public List<OrderDetails> getByOrderNumber(@PathVariable int order_number){
		if(orderDetailsServices.getByOrderNumber(order_number).size()>0) {
        return orderDetailsServices.getByOrderNumber(order_number);
		}
		else {
			throw new NotFoundException("No order detail found for " + order_number);
		}
    }
    
    @GetMapping("/total_amount/{order_number}")
    public List<Double> getAmountByOrderNumber(@PathVariable int order_number){
    	if (!(orderDetailsServices.getAmountByOrderNumber(order_number).isEmpty())) {
        return orderDetailsServices.getAmountByOrderNumber(order_number);
    	}
    	else {
    		throw new NotFoundException("No details found for the given attribute " + order_number);
    	}
    }
    
    @GetMapping("/total_sale")
    public int getByTotalSale(){
    	if(orderDetailsServices.getByTotalSale() != 0) {
        return orderDetailsServices.getByTotalSale();
    	}
    	else {
    		throw new NotFoundException("Total sale is 0");
    	}
    }
    
    @PreAuthorize("hasRole('USER')")
    
    @PostMapping(value = "/add",consumes ="application/json")
    
    public OrderDetails add(@RequestBody OrderDetails orderDetails) {
    	return orderDetailsServices.add(orderDetails);
    }
    
    

	
	
	

}

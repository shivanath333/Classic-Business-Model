package com.sprint.cbm.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.cbm.entities.Order;
import com.sprint.cbm.entities.OrderDetails;
import com.sprint.cbm.entities.Product;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.repository.OrderDetailsRepo;

@Service
public class OrderDetailsServices {

	@Autowired

	private OrderDetailsRepo orderDetailsRepo;

	public List<OrderDetails> getAll() {
		return orderDetailsRepo.findAll();

	}

	public int getOrderDetailsCount(String attribute) {

		if (attribute.equalsIgnoreCase("quantity")) {

			return orderDetailsRepo.sumQuantityOrdered();
		}

		else if (attribute.equalsIgnoreCase("price")) {

			return orderDetailsRepo.countByPriceEachNotNull();
		}
		
		else if (attribute.equalsIgnoreCase("order number")) {
			return orderDetailsRepo.countByOrderLineNumberNotNull();
			
		}

		else {
			throw new NotFoundException("No details found for given attribute ");
		}

	}
	public List<OrderDetails> getByOrderNumber(int orderNumber){
        return orderDetailsRepo.findByOrderNumber(orderNumber);
    }
    public List<Double> getAmountByOrderNumber(int order_number) {
        return orderDetailsRepo.findAmountByOrderNumber(order_number);
    }
    public int getByTotalSale() {
        return orderDetailsRepo.findByTotalSale();
    }
    
    public OrderDetails add(OrderDetails orderDetails) {
    	return orderDetailsRepo.save(orderDetails);
    }

}

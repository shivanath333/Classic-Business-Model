package com.sprint.cbm.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.cbm.entities.Order;
import com.sprint.cbm.entities.Product;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.repository.OrderRepo;

/**
 * Order Services.
 *
 * This service class provides various operations related to orders, such as retrieving order information,
 * updating order details, and performing queries on orders based on different criteria.
 */
@Service
public class OrderServices {

	@Autowired

	private OrderRepo orderRepo;

	/**
	 * Retrieves all orders.
	 *
	 * @return List of all orders
	 */
	// to fetch all the data from the order table
	public List<Order> getAll() {
		return orderRepo.findAll();

	}
	
	/**
	 * Retrieves an order by order number.
	 *
	 * @param number The order number
	 * @return The order with the specified order number, or null if not found
	 */

	public Order getOrderByNumber(int number) {
		return orderRepo.findById(number);
	}

	/**
	 * Retrieves orders by order date.
	 *
	 * @param date The order date
	 * @return List of orders with the specified order date
	 */
	public List<Order> getOrderByDate(Date date) {
		return orderRepo.findByOrderDate(date);
	}
	
	/**
	 * Retrieves orders by required date.
	 *
	 * @param date The required date
	 * @return List of orders with the specified required date
	 */

	public List<Order> getOrderByRequiredDate(Date date) {
		return orderRepo.findByRequiredDate(date);
	}
	/**
	 * Retrieves orders by shipped date.
	 *
	 * @param date The shipped date
	 * @return List of orders with the specified shipped date
	 */

	public List<Order> getOrderByShippedDate(Date date) {
		return orderRepo.findByShippedDate(date);
	}

	/**
	 * Retrieves orders by status.
	 *
	 * @param status The order status
	 * @return List of orders with the specified status
	 */
	public List<Order> getOrderByStatus(String status) {
		return orderRepo.findByStatus(status);
	}

	public List<Order> getByCustomerNumber(int customerNumber) {
		return orderRepo.findByCustomerNumber(customerNumber);
	}

	public List<Order> getByStatusAndCustNumber(int customerNumber, String status) {
		return orderRepo.findByStatusAndCustNumber(customerNumber, status);
	}

	public List<Product> getProductByOrderNumber(int orderNumber) {
		return orderRepo.findProductByOrderNumber(orderNumber);
	}

	public List<String> getProductNameByOrderNumber(int orderNumber) {
		return orderRepo.findProductNameByOrderNumber(orderNumber);
	}

	public List<Product> getAllProducts() {
		return orderRepo.findAllProducts();
	}

	public List<Order> getByOrderStatus(String status) {
		return orderRepo.findByOrderStatus(status);
	}

	public Order createOrder(Order order) {
		return orderRepo.save(order);

	}

	public List<Order> createList(List<Order> orders) {
		return orderRepo.saveAll(orders);
	}

	public void updateShippedDate(int orderNumber, Date shippedDate) {
		Order order = orderRepo.findById(orderNumber);
		if (order != null) {

			order.setShippedDate(shippedDate);
			orderRepo.save(order);
		} else {
			throw new NotFoundException("Order not found");
		}

	}
	

    public Order updateOrderStatusAndDate(int orderNumber, String status,Date required) {
        Order order = orderRepo.findById(orderNumber);
        if(order != null) {
        order.setStatus(status);
        order.setRequiredDate(required);
        
        return orderRepo.save(order);
        }
        else {
        	throw new NotFoundException("Order not found");
        }
    }
        
    public Order updateDetails(int id, Order order) {

        Order order2 = orderRepo.findById(id);

        order2.setOrderDate(order.getOrderDate());
        order2.setRequiredDate(order.getRequiredDate());
        order2.setShippedDate(order.getShippedDate());
        order2.setStatus(order.getStatus());
        order2.setComment(order.getComment());
        
        return orderRepo.save(order2);

    }

}

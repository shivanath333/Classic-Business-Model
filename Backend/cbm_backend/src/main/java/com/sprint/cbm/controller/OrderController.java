package com.sprint.cbm.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

import com.sprint.cbm.entities.Employees;
import com.sprint.cbm.entities.Order;
import com.sprint.cbm.entities.Product;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.services.OrderServices;

import io.swagger.v3.oas.annotations.Operation;



/**
 * Controller class for handling order-related endpoints.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

	@Autowired
	private OrderServices orderServices;

	 /**
     * Retrieves all orders.
     *
     * @return List of all orders
     */
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("")

	public List<Order> getAll() {
		return orderServices.getAll();
	}
	/**
     * Retrieves an order by its number.
     *
     * @param number The number of the order
     * @return The order with the specified number
     * @throws NotFoundException if the order is not found
     */

	@GetMapping("/number/{number}")
	public Order getOrdersByNumber(@PathVariable int number) {
		if (orderServices.getOrderByNumber(number) != null) {
			return orderServices.getOrderByNumber(number);
		} else {
			throw new NotFoundException("Order not found !!");
		}
	}
	/**
     * Retrieves orders by order date.
     *
     * @param date The order date
     * @return List of orders with the specified order date
     * @throws NotFoundException if no orders are found
     */

	@GetMapping("/order_date/{date}")
	public List<Order> getOrdersByDate(@PathVariable Date date) {

		List<Order> order = orderServices.getOrderByDate(date);
		if (order.isEmpty()) {
			return order;
		} else {
			throw new NotFoundException("orders not found");
		}

	}
	
	 /**
     * Retrieves orders by required date.
     *
     * @param date The required date
     * @return List of orders with the specified required date
     * @throws NotFoundException if no orders are found
     */

	@GetMapping("/required_date/{date}")
	public List<Order> getOrdersByRequiredDate(@PathVariable Date date) {
		if (orderServices.getOrderByRequiredDate(date).size() > 0) {
			return orderServices.getOrderByRequiredDate(date);
		} else {
			throw new NotFoundException("Orders not found ,in the given date");
		}

	}
	 /**
     * Retrieves orders by shipped date.
     *
     * @param date The shipped date
     * @return List of orders with the specified shipped date
     * @throws NotFoundException if no orders are found
     */

	@GetMapping("/shipped_date/{date}")
	public List<Order> getOrdersByShippedDate(@PathVariable Date date) {
		if (orderServices.getOrderByShippedDate(date).size() > 0) {
			return orderServices.getOrderByShippedDate(date);
		} else {
			throw new NotFoundException("No order found in the given date");
		}
	}
	 /**
     * Retrieves orders by status.
     *
     * @param status The status of the orders to retrieve
     * @return List of orders with the specified status
     * @throws NotFoundException if no orders are found
     */

	@GetMapping("/status/{status}")
	public List<Order> getOrdersByStatus(@PathVariable String status) {
		if (orderServices.getOrderByStatus(status).size() > 0) {
			return orderServices.getOrderByStatus(status);
		} else {
			throw new NotFoundException("No order found in the given date");
		}
	}
	
	 /**
     * Retrieves orders by customer number.
     *
     * @param customerNumber The customer number
     * @return List of orders associated with the specified customer number
     * @throws NotFoundException if no orders are found
     */

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/get/{customerNumber}")
	public List<Order> getByCustomerNumber(@PathVariable int customerNumber) {
		if (orderServices.getByCustomerNumber(customerNumber).size() > 0) {
			return orderServices.getByCustomerNumber(customerNumber);
		} else {
			throw new NotFoundException("No orders found for the given customer number");
		}
	}

	@GetMapping("/start/{customerNumber}/{status}")
	public List<Order> getByStatusAndCustNumber(@PathVariable int customerNumber, @PathVariable String status) {
		if (orderServices.getByStatusAndCustNumber(customerNumber, status).size() > 0) {
			return orderServices.getByStatusAndCustNumber(customerNumber, status);
		} else {
			throw new NotFoundException("No orders found for given request");
		}
	}

	@GetMapping("/{orderNumber}/products")
	public List<Product> getProductByOrderNumber(@PathVariable int orderNumber) {
		if (orderServices.getProductByOrderNumber(orderNumber).size() > 0) {
			return orderServices.getProductByOrderNumber(orderNumber);
		} else {
			throw new NotFoundException("No product found for given order number");
		}
	}

	@GetMapping("/{orderNumber}/productNames")
	public List<String> getProductNameByOrderNumber(@PathVariable int orderNumber) {
		if (orderServices.getProductNameByOrderNumber(orderNumber).size() > 0) {
			return orderServices.getProductNameByOrderNumber(orderNumber);
		} else {
			throw new NotFoundException("No product name found for given order number");
		}
	}

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		if (orderServices.getAllProducts().size() > 0) {
			return orderServices.getAllProducts();
		} else {
			throw new NotFoundException("No products found");
		}
	}

	@GetMapping("/{status}/orders")
	public List<Order> getByOrderStatus(@PathVariable String status) {
		if (orderServices.getByOrderStatus(status).size() > 0) {
			return orderServices.getByOrderStatus(status);
		} else {
			throw new NotFoundException("No order found");
		}
	}


	@PreAuthorize("hasRole('USER')")
	
	@PostMapping("/create")
	
	 public Order add(@RequestBody Order order) {
		return orderServices.createOrder(order);
	}
	
	
	@PostMapping("/add/all")
	
	public List<Order> add(@RequestBody List<Order> order){
		return orderServices.createList(order);
	}
	
	@PutMapping("/{order_number}/{shipped_date}")
    public ResponseEntity<String> updateShippedDate(
            @PathVariable("order_number") int orderNumber,
            @PathVariable("shipped_date")  Date shippedDate) {
        orderServices.updateShippedDate(orderNumber, shippedDate);
        return ResponseEntity.ok("Updated");
    }
	
	
	@PutMapping("/updates/{id}")
	
	public ResponseEntity<String> update(@PathVariable int id,@RequestBody String status,@RequestBody Date required){
		orderServices.updateOrderStatusAndDate(id, status, required);
		return ResponseEntity.ok("Updated successfully");
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    @Operation(summary = "Update Order by Order Number")
    public ResponseEntity<Order> update(@PathVariable int id, @RequestBody Order order) {
        Order orderNew = orderServices.getOrderByNumber(id);
        if (orderNew != null) {
            orderServices.updateDetails(id, order);
            return ResponseEntity.ok(order);
        } else {
            throw new NotFoundException("Order not found");
        }
    }
	
	

}

package com.sprint.cbm.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.cbm.entities.Order;
import com.sprint.cbm.entities.OrderDetails;
import com.sprint.cbm.entities.Product;

//
@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

	Order findById(int number);

	List<Order> findByOrderDate(Date date);

	List<Order> findByRequiredDate(Date date);

	List<Order> findByShippedDate(Date date);

	List<Order> findByStatus(String status);

	@Query("Select o from Order o JOIN o.customer c where c.id=:customerNumber")
	List<Order> findByCustomerNumber(@Param("customerNumber") int customerNumber);

//    
	@Query("Select o from Order o JOIN o.customer c where c.id=:customerNumber AND o.status=:status")
	List<Order> findByStatusAndCustNumber(@Param("customerNumber") int customerNumber, @Param("status") String status);

//    
	@Query("Select p from Product p JOIN p.details od JOIN od.orders o where o.id=:orderNumber")
	List<Product> findProductByOrderNumber(@Param("orderNumber") int orderNumber);

//    
	@Query("Select p.name from Product p JOIN p.details od JOIN od.orders o where o.id=:orderNumber")
	List<String> findProductNameByOrderNumber(@Param("orderNumber") int orderNumber);

//    
	@Query("Select p from Product p JOIN p.details od JOIN od.orders o")
	List<Product> findAllProducts();

//    
	@Query("Select o from Order o where o.status =:status AND o.requiredDate = o.shippedDate")
	List<Order> findByOrderStatus(@Param("status") String status);

	
}

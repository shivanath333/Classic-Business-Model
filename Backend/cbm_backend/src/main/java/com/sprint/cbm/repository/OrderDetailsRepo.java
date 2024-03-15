package com.sprint.cbm.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.cbm.entities.OrderDetails;
import com.sprint.cbm.entities.OrderDetailsId;
import com.sprint.cbm.entities.Product;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, OrderDetailsId> {

	int countByQuantityOrderedGreaterThan(int a);

	int countByPriceEachNotNull();
	
	@Query("SELECT SUM(od.quantityOrdered) FROM OrderDetails od")
    int sumQuantityOrdered();

	int countByOrderLineNumberNotNull();
	 
	@Query("Select od From OrderDetails od JOIN od.orders o where o.id=:orderNumber")
	List<OrderDetails> findByOrderNumber(@Param("orderNumber") int orderNumber);

	@Query("Select sum(od.priceEach) From OrderDetails od JOIN od.orders o where o.id=:orderNumber")
	List<Double> findAmountByOrderNumber(@Param("orderNumber") int orderNumber);

	@Query("Select sum(od.quantityOrdered) From OrderDetails od")
	int findByTotalSale();
	

	



		
	



	
	
	 
	
	
	
	

}

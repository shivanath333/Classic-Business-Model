package com.sprint.cbm.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.cbm.entities.Product;

@Repository

public interface ProductRepo extends JpaRepository<Product, String> {

	Optional<Product> findByCode(String id);

	Product findByName(String productName);

	List<Product> findByProductScale(String productScale);

	List<Product> findByProductVendor(String productVendor);

	@Query("select sum(od.quantityOrdered) from OrderDetails od JOIN od.product p where p.code=:id")
	double findByTotalOrdered(@Param("id") String id);

	@Query("select sum(od.priceEach) from OrderDetails od JOIN od.product p where p.code=:id")
	double findByTotalSale(@Param("id") String id);
//
	@Query("SELECT p.name AS productName, SUM(od.priceEach) AS totalSale "
			+ "FROM OrderDetails od JOIN od.product p " + "GROUP BY p.name")
	LinkedList<Map<String, Double>> findByTotalSale();
//
	@Query("SELECT p FROM Product p " + "WHERE p.code IN (SELECT od.product.code FROM OrderDetails od "
			+ "GROUP BY od.product.code " + "ORDER BY SUM(od.quantityOrdered) DESC)")
	List<Product> findByProductDetails();
	
	 List<Product> findByProductLineProductLine(String productLine);

	

}

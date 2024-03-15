package com.sprint.cbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sprint.cbm.entities.ProductLine;

@Repository
public interface ProductLineRepo extends JpaRepository<ProductLine, Integer> {

	ProductLine findByProductLine(String name);

}
package com.sprint.cbm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.cbm.entities.ProductLine;
import com.sprint.cbm.repository.ProductLineRepo;

@Service
public class ProductLineServices {
	
	
	@Autowired
	public ProductLineRepo productLineRepo;
	
	
	public List<ProductLine> getAll(){
		return productLineRepo.findAll();
	}

	
	public ProductLine getById(String name) {
		return productLineRepo.findByProductLine(name);
		
		
	}
	
	public ProductLine updateProductLine(String name,String description) {
		
		
		
		ProductLine productLine = productLineRepo.findByProductLine(name);
		productLine.setDescription(description);
		return productLineRepo.save(productLine);
	}
	
	
	public ProductLine createProductLine(ProductLine productLine) {
//		ProductLine productLine2 = new ProductLine();
		
//		productLine2.setProductLine(productLine.getProductLine());
//		productLine2.setDescription(productLine.getDescription());
		
		
		return productLineRepo.save(productLine);
	}
		
				
		
		
		
		
		
	
}

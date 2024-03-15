package com.sprint.cbm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sprint.cbm.entities.ProductLine;
import com.sprint.cbm.repository.ProductLineRepo;

public class ProductLineServiceTest {

	@Mock
	private ProductLineRepo productLineRepo;

	@InjectMocks
	private ProductLineServices productLineService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAll_ReturnsListOfProductLines() {
		// Arrange
		List<ProductLine> productLines = new ArrayList<>();
		productLines.add(new ProductLine());
		productLines.add(new ProductLine());

		when(productLineRepo.findAll()).thenReturn(productLines);

		// Act
		List<ProductLine> result = productLineService.getAll();

		// Assert
		assertEquals(productLines, result);
		verify(productLineRepo).findAll();
	}

	@Test
	void testGetById_ExistingName_ReturnsProductLine() {
		// Arrange
		String name = "Classic Cars";
		ProductLine expectedProductLine = new ProductLine();

		when(productLineRepo.findByProductLine(name)).thenReturn(expectedProductLine);

		// Act
		ProductLine result = productLineService.getById(name);

		// Assert
		assertEquals(expectedProductLine, result);
		verify(productLineRepo).findByProductLine(name);
	}

	@Test
	void testUpdateProductLine_ExistingName_ReturnsUpdatedProductLine() {
		// Arrange
		String name = "Classic Cars";
		String description = "'Attention car enthusiasts: Make your wildest car ownership dreams come true. Whether you are looking for classic muscle cars, dream sports cars or movie-inspired miniatures, "
				+ "you will find great choices in this category. These replicas feature superb attention to detail and craftsmanship and offer features such as working steering system, "
				+ "opening forward compartment, opening rear trunk with removable spare wheel, 4-wheel independent spring suspension, and so on. The models range in size from 1:10 to 1:24 scale "
				+ "and include numerous limited edition "
				+ "and several out-of-production vehicles. All models include a certificate of authenticity from their manufacturers and come fully assembled and ready for display in the home or office.";
		ProductLine productLine = new ProductLine();
		productLine.setProductLine(name);

		when(productLineRepo.findByProductLine(name)).thenReturn(productLine);
		when(productLineRepo.save(productLine)).thenReturn(productLine);

		// Act
		ProductLine updatedProductLine = productLineService.updateProductLine(name, description);

		// Assert
		assertEquals(description, updatedProductLine.getDescription());
		verify(productLineRepo).findByProductLine(name);
		verify(productLineRepo).save(productLine);
	}

	@Test
	void testCreateProductLine_ValidProductLine_ReturnsCreatedProductLine() {
		// Arrange
		ProductLine productLine = new ProductLine();
		when(productLineRepo.save(productLine)).thenReturn(productLine);

		// Act
		ProductLine createdProductLine = productLineService.createProductLine(productLine);

		// Assert
		assertEquals(productLine, createdProductLine);
		verify(productLineRepo).save(productLine);
	}
}

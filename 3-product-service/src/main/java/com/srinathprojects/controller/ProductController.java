package com.srinathprojects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.srinathprojects.dto.ProductRequestDto;
import com.srinathprojects.dto.ProductResponseDto;
import com.srinathprojects.service.ProductService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	
	
	@PostMapping
	@ResponseStatus(code= HttpStatus.CREATED)
	public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) {
		
		log.info("Product service : ProductController {}", productRequestDto.getName());
		
		return productService.addProduct(productRequestDto);
		
	}
	
	@GetMapping("/{productId}")
	public ProductResponseDto getProduct(@PathVariable Long productId) {
		
		log.info("Product service : getProduct {}", productId);
		
		return productService.getProductById(productId);
	}
	
	@GetMapping("/exist/{productId}")
	public boolean isProductExist(@PathVariable Long productId) {
		
		log.info("Product service : isProductExist {}", productId);
		
		return productService.isProductExists(productId);
	}

}

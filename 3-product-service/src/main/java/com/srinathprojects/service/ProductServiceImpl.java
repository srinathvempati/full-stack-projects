package com.srinathprojects.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinathprojects.dto.ProductRequestDto;
import com.srinathprojects.dto.ProductResponseDto;
import com.srinathprojects.entity.Product;
import com.srinathprojects.repository.ProductRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
		
		log.info("Product service layer : addProduct {}", productRequestDto.getName() );

		Product product = new Product();
		BeanUtils.copyProperties(productRequestDto, product);

		Product dbProduct = productRepository.save(product);

		log.info("Product service layer : addProduct return satetment {}", productRequestDto.getName() );
		
		return mapToDto(dbProduct);
	}

	public ProductResponseDto mapToDto(Product product) {
		ProductResponseDto response = new ProductResponseDto();
		BeanUtils.copyProperties(product, response);
		return response;

	}

	@Override
	public boolean isProductExists(Long productId) {
		
		log.info("roduct service layer : isProductExist {}", productId);
		
		return productRepository.existsById(productId);
	}

	@Override
	public ProductResponseDto getProductById(Long productId) {
		
		log.info("roduct service layer : getProductById {}", productId);
		
		return productRepository.findById(productId)
				.map( p -> mapToDto(p))
				.orElseThrow(() -> new RuntimeException("Product Id not found in DB"));
	}

}

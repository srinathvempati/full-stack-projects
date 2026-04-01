package com.srinathprojects.service;

import com.srinathprojects.dto.ProductRequestDto;
import com.srinathprojects.dto.ProductResponseDto;

public interface ProductService {
	
	ProductResponseDto addProduct(ProductRequestDto productRequestDto);
	
    boolean isProductExists(Long productId);
    
    ProductResponseDto getProductById(Long productId);

}

package com.srinathprojects.feignClients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// This Interface will be use for communicating with PRODUCT SERVICE and USER SERVICE (EUREKA FEIGN CLIENT)

@FeignClient(name = "PRODUCT-SERVICE", path = "/products")
@LoadBalancerClient     // this loadbalancer will take care if we have PRODUCT Instances are there 
public interface ProductFeignClient {
	

	// This URL we copy from product controller class
	@GetMapping("/exist/{productId}")
	public boolean isProductExist(@PathVariable("productId") Long productId);

}

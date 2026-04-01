package com.srinathprojects.feignClients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.srinathprojects.dto.UserDto;

//This Interface will be use for communicating with PRODUCT SERVICE and USER SERVICE (EUREKA FEIGN CLIENT)

@FeignClient(name = "user-service", path = "/api/users")
@LoadBalancerClient     // this loadbalancer will take care if we have mutiple Instances are there 
public interface UserFeignClient {

	@GetMapping("/{userId}")
	public UserDto fetchUser(@PathVariable("userId") Integer userId) ;
		
}

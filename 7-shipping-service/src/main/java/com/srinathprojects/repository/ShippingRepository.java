package com.srinathprojects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srinathprojects.models.ShippingInfo;
import java.util.List;


@Repository
public interface ShippingRepository extends JpaRepository<ShippingInfo, Long>{
	
	
	ShippingInfo findByOrderId(Long orderId);

}

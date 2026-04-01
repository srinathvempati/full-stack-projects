package com.srinathprojects.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srinathprojects.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}

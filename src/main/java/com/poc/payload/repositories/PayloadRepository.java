package com.poc.payload.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.payload.dto.PayloadDTO;

public interface PayloadRepository extends JpaRepository<PayloadDTO, Long> {

}

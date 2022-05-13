package com.poc.payload.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.payload.entities.Payload;

public interface PayloadRepository extends JpaRepository<Payload, Long> {

}

package com.poc.payload.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.payload.entities.Payload;
import com.poc.payload.repositories.PayloadRepository;

@Service
public class PayloadService {
	
	@Autowired
	private PayloadRepository repo;
	
	public List<Payload> findAll() {
		return repo.findAll();
	}
	
	public Payload findById(Long id) {
		Payload payload = repo.findById(id).orElseThrow();
		return payload;
	}
}

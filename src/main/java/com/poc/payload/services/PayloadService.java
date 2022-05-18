package com.poc.payload.services;

import java.util.List;

import com.poc.payload.entities.Payload;

public interface PayloadService {
	
	List<Payload> findAll();
	
	Payload findById(Long id);
	
	Payload save(Payload payload);
	
	Payload update(Payload payloadUpdate, Long id);
	
	void delete(Long id);
}

package com.poc.payload.services;

import java.util.List;

import com.poc.payload.domain.Payload;
import com.poc.payload.domain.dto.PayloadDTO;


public interface PayloadService {
	
	List<Payload> findAll();
	
	Payload findById(Long id);
	
	Payload save(PayloadDTO payloadDto);
	
	Payload update(Payload payloadUpdate, Long id);
	
	void delete(Long id);
}

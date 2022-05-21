package com.poc.payload.services;

import java.util.List;

import com.poc.payload.dto.PayloadDTO;

public interface PayloadService {
	
	List<PayloadDTO> findAll();
	
	PayloadDTO findById(Long id);
	
	PayloadDTO save(PayloadDTO payload);
	
	PayloadDTO update(PayloadDTO payloadUpdate, Long id);
	
	void delete(Long id);
}

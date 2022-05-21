package com.poc.payload.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.payload.dto.PayloadDTO;
import com.poc.payload.repositories.PayloadRepository;
import com.poc.payload.services.PayloadService;

@Service
public class PayloadServiceImpl implements PayloadService {
	
	@Autowired
	private PayloadRepository repository;
	
	@Override
	public List<PayloadDTO> findAll() {
		return repository.findAll();
	}
	
	@Override
	public PayloadDTO findById(Long id) {
		PayloadDTO payload = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found: " + id));
		return payload;
	}
	
	@Override
	public PayloadDTO save(PayloadDTO payload) {
		return repository.save(payload);
	}
	
	@Override
	public PayloadDTO update(PayloadDTO payloadUpdate, Long id) {
		PayloadDTO payload = findById(id);
		PayloadDTO payloadUpdated = updateData(payload, payloadUpdate);
		return repository.save(payloadUpdated);
		
	}
	
	@Override
	public void delete(Long id) {
		PayloadDTO payload = findById(id);
		repository.delete(payload);
	}
	
	private PayloadDTO updateData(PayloadDTO payload, PayloadDTO payloadUpdate) {
		payload.setFormCode(payloadUpdate.getFormCode());
		payload.setFileName(payloadUpdate.getFileName());
		payload.setContent(payloadUpdate.getContent());
		payload.setToken(payloadUpdate.getToken());
		return payload;
	}
	
 }

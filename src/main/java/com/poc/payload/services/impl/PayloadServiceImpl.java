package com.poc.payload.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.payload.entities.Payload;
import com.poc.payload.repositories.PayloadRepository;
import com.poc.payload.services.PayloadService;

@Service
public class PayloadServiceImpl implements PayloadService {
	
	@Autowired
	private PayloadRepository repo;
	
	@Override
	public List<Payload> findAll() {
		return repo.findAll();
	}
	
	@Override
	public Payload findById(Long id) {
		Payload payload = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found: " + id));
		return payload;
	}
	
	@Override
	public Payload save(Payload payload) {
		return repo.save(payload);
	}
	
	@Override
	public Payload update(Payload payloadUpdate, Long id) {
		Payload payload = findById(id);
		Payload payloadUpdated = updateData(payload, payloadUpdate);
		return repo.save(payloadUpdated);
		
	}
	
	@Override
	public void delete(Long id) {
		Payload payload = findById(id);
		repo.delete(payload);
	}
	
	private Payload updateData(Payload payload, Payload payloadUpdate) {
		payload.setFormCode(payloadUpdate.getFormCode());
		payload.setFileName(payloadUpdate.getFileName());
		payload.setContent(payloadUpdate.getContent());
		payload.setToken(payloadUpdate.getToken());
		return payload;
	}
	
 }

package com.poc.payload.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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
		Payload payload = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found: " + id));
		return payload;
	}
	
	public Payload save(Payload payload) {
		return repo.save(payload);
	}
	
	public Payload update(Payload payloadUpdate, Long id) {
		Payload payload = findById(id);
		Payload payloadUpdated = updateData(payload, payloadUpdate);
		return repo.save(payloadUpdated);
		
	}
	
	private Payload updateData(Payload payload, Payload payloadUpdate) {
		payload.setFormCode(payloadUpdate.getFormCode());
		payload.setFileName(payloadUpdate.getFileName());
		payload.setContent(payloadUpdate.getContent());
		payload.setToken(payloadUpdate.getToken());
		return payload;
	}
	
	public void delete(Long id) {
		Payload payload = findById(id);
		repo.delete(payload);
	}
 }

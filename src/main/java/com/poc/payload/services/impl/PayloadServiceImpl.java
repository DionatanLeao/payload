package com.poc.payload.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.payload.domain.Payload;
import com.poc.payload.domain.dto.PayloadDTO;
import com.poc.payload.repositories.PayloadRepository;
import com.poc.payload.services.PayloadService;

@Service
public class PayloadServiceImpl implements PayloadService {
	
	@Autowired
	private PayloadRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<Payload> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Payload findById(Long id) {
		Payload payload = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found: " + id));
		return payload;
	}
	
	@Override
	public Payload save(PayloadDTO payloadDto) {
		return repository.save(mapper.map(payloadDto, Payload.class));
	}
	
	@Override
	public Payload update(Payload payloadUpdate, Long id) {
		Payload payload = findById(id);
		Payload payloadUpdated = updateData(payload, payloadUpdate);
		return repository.save(payloadUpdated);
		
	}
	
	@Override
	public void delete(Long id) {
		Payload payload = findById(id);
		repository.delete(payload);
	}
	
	private Payload updateData(Payload payload, Payload payloadUpdate) {
		payload.setFormCode(payloadUpdate.getFormCode());
		payload.setFileName(payloadUpdate.getFileName());
		payload.setContent(payloadUpdate.getContent());
		payload.setToken(payloadUpdate.getToken());
		return payload;
	}
	
 }

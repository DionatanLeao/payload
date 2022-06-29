package com.poc.payload.controllers;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.poc.payload.domain.Payload;
import com.poc.payload.domain.dto.PayloadDTO;
import com.poc.payload.services.PayloadService;

@RestController
@RequestMapping("/payloads")
@CrossOrigin(origins = "http://localhost:8080", methods = RequestMethod.PUT)
public class PayloadController {
	
	@Autowired
	private PayloadService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<Payload>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PayloadDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(mapper.map(service.findById(id), PayloadDTO.class));		
	}
	
	@PostMapping
	public ResponseEntity<Payload> save(@RequestBody Payload payload, 
			@RequestHeader(name = "token", defaultValue = "") String token) {
		payload.setToken(token);
		Payload payloadSaved = service.save(payload);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(payload.getId()).toUri();
		return ResponseEntity.created(uri).body(payloadSaved);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Payload> update(@RequestBody Payload payloadUpdate, @PathVariable Long id, 
			@RequestHeader(name = "token", defaultValue = "") String token) {
		payloadUpdate.setToken(token);
		Payload payload = service.update(payloadUpdate, id);
		return ResponseEntity.ok().body(payload);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id, 
			@RequestHeader(name = "token", defaultValue = "") String token) { 
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}

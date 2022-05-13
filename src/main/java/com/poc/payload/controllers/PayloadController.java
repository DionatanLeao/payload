package com.poc.payload.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.poc.payload.entities.Payload;
import com.poc.payload.services.PayloadService;

@RestController
@RequestMapping("/payloads")
public class PayloadController {
	
	@Autowired
	private PayloadService service;
	
	@GetMapping
	public ResponseEntity<List<Payload>> findAll() {
		List<Payload> payloads = service.findAll();
		return ResponseEntity.ok().body(payloads);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Payload> findById(@PathVariable Long id) {
		Payload payload = service.findById(id);
		return ResponseEntity.ok().body(payload);		
	}
	
	@PostMapping
	public ResponseEntity<Payload> save(@RequestBody Payload payload) {
		Payload payloadSaved = service.save(payload);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(payload.getId()).toUri();
		return ResponseEntity.created(uri).body(payloadSaved);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Payload> update(@RequestBody Payload payloadUpdate, @PathVariable Long id) {
		Payload payload = service.update(payloadUpdate, id);
		return ResponseEntity.ok().body(payload);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) { 
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}

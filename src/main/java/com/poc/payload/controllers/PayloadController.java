package com.poc.payload.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
	
	private static final String ID = "/{id}";

	@Autowired
	private PayloadService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<PayloadDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll().stream().map(x -> mapper.map(x, PayloadDTO.class)).collect(Collectors.toList()));
	}
	
	@GetMapping(ID)
	public ResponseEntity<PayloadDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(mapper.map(service.findById(id), PayloadDTO.class));		
	}
	
	@PostMapping
	public ResponseEntity<PayloadDTO> save(@RequestBody PayloadDTO payloadDto, 
			@RequestHeader(name = "token", defaultValue = "") String token) {
		payloadDto.setToken(token);
		Payload response = service.save(payloadDto);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path(ID)
					.buildAndExpand(response.getId()).toUri();
		
		return ResponseEntity.created(uri).body(mapper.map(response, PayloadDTO.class));
	}
	
	@PutMapping(ID)
	public ResponseEntity<PayloadDTO> update(@RequestBody PayloadDTO payloadUpdate, @PathVariable Long id, 
			@RequestHeader(name = "token", defaultValue = "") String token) {
		payloadUpdate.setToken(token);
		payloadUpdate.setId(id);
		return ResponseEntity.ok().body(mapper.map(service.update(payloadUpdate), PayloadDTO.class));
	}
	
	@DeleteMapping(ID)
	public ResponseEntity<Void> delete(@PathVariable Long id, 
			@RequestHeader(name = "token", defaultValue = "") String token) { 
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}

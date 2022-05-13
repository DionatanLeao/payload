package com.poc.payload.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

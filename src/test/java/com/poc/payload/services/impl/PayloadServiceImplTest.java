package com.poc.payload.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.poc.payload.entities.Payload;
import com.poc.payload.repositories.PayloadRepository;

class PayloadServiceImplTest {
	
	@InjectMocks
	private PayloadServiceImpl service;
	
	@Mock
	private PayloadRepository repository;
	
	@Mock
	private Payload payload;
	
	private void startPayload() {
		payload = new Payload(1L, "x123", "arquivo_1", "Texto genérico para teste unitário", "R2VyYW5kbyB0b2tlbiBTU08=");
	}
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startPayload();
	}

	@Test
	void findAll() {
		when(repository.findAll()).thenReturn(List.of(payload));
		
		List<Payload> response = service.findAll();
		
		assertNotNull(response);
		assertEquals(Payload.class, response.get(0).getClass());	
		
		assertEquals(1L, response.get(0).getId());
		assertEquals("x123", response.get(0).getFormCode());
		assertEquals("arquivo_1", response.get(0).getFileName());
		assertEquals("Texto genérico para teste unitário", response.get(0).getContent());

	}

}

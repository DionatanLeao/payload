package com.poc.payload.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.poc.payload.entities.Payload;
import com.poc.payload.repositories.PayloadRepository;

class PayloadServiceImplTest {
	
	private static final String TOKEN = "R2VyYW5kbyB0b2tlbiBTU08=";

	private static final String CONTENT = "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis";

	private static final String FILE_NAME = "arquivo_1";

	private static final String FORM_CODE = "x123";

	private static final long ID = 1L;

	private static final String NOT_FOUND = "Not found: " + ID;

	@InjectMocks
	private PayloadServiceImpl service;
	
	@Mock
	private PayloadRepository repository;
	
	private Payload payload;
	
	private Optional<Payload> optionalPayload;
	
	private void startPayload() {
		payload = new Payload(ID, FORM_CODE, FILE_NAME, CONTENT, TOKEN);
		optionalPayload = Optional.of(new Payload(ID, FORM_CODE, FILE_NAME, CONTENT, TOKEN));
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
		
		assertEquals(ID, response.get(0).getId());
		assertEquals(FORM_CODE, response.get(0).getFormCode());
		assertEquals(FILE_NAME, response.get(0).getFileName());
		assertEquals(CONTENT, response.get(0).getContent());

	}
	
	@Test
	void findById() {
		when(repository.findById(Mockito.anyLong())).thenReturn(optionalPayload);
		
		Payload response = service.findById(ID);
		
		assertNotNull(response);
		assertEquals(Payload.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(FORM_CODE, response.getFormCode());
		assertEquals(FILE_NAME, response.getFileName());
		assertEquals(CONTENT, response.getContent());

	}

	@Test
	void findByIdEntityNotFoundException() {
		when(repository.findById(Mockito.anyLong())).thenThrow(new EntityNotFoundException(NOT_FOUND));

		try {
			service.findById(ID);
		} catch (Exception e) {
			assertEquals(EntityNotFoundException.class, e.getClass());
			assertEquals(NOT_FOUND, e.getMessage());
		}	
		
	}
	
	@Test
	void save() {
		when(repository.save(Mockito.any())).thenReturn(payload);
		
		Payload response = service.save(payload);
		
		assertNotNull(response);
		assertEquals(Payload.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(FORM_CODE, response.getFormCode());
		assertEquals(FILE_NAME, response.getFileName());
		assertEquals(CONTENT, response.getContent());
	}
}

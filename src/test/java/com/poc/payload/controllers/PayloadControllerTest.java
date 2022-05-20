package com.poc.payload.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.poc.payload.entities.Payload;
import com.poc.payload.services.impl.PayloadServiceImpl;

@SpringBootTest
class PayloadControllerTest {
	
	private static final String TOKEN = "token";
	private static final String CONTENT = "content";
	private static final String FILE_NAME = "arquivo_0";
	private static final String FORM_CODE = "x123";
	private static final long ID = 1L;
	private static final int INDEX = 0;
	
	@InjectMocks
	private PayloadController controller;
	
	@Mock
	private PayloadServiceImpl service;
	
	private Payload payload;
	
	void start() {
		payload = new Payload(ID, FORM_CODE, FILE_NAME, CONTENT, TOKEN);
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		start();
	}

	@Test
	void findAll() {
		when(service.findAll()).thenReturn(List.of(payload));
		
		ResponseEntity<List<Payload>> response = controller.findAll();
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(Payload.class, response.getBody().get(INDEX).getClass());
		
		assertEquals(ID, response.getBody().get(INDEX).getId());
		assertEquals(FORM_CODE, response.getBody().get(INDEX).getFormCode());
		assertEquals(FILE_NAME, response.getBody().get(INDEX).getFileName());
		assertEquals(CONTENT, response.getBody().get(INDEX).getContent());
		assertEquals(TOKEN, response.getBody().get(INDEX).getToken());
		
	}

}

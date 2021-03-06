package com.poc.payload.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.poc.payload.domain.FieldList;
import com.poc.payload.domain.Payload;
import com.poc.payload.domain.dto.FieldListDTO;
import com.poc.payload.domain.dto.PayloadDTO;
import com.poc.payload.services.impl.PayloadServiceImpl;

@SpringBootTest
class PayloadControllerTest {
	
	private static final String TYPE = "type";
	private static final String FIELD_NAME = "fieldName";
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
	
	@Mock
	private ModelMapper mapper;
	
	private Payload payload;
	
	private PayloadDTO payloadDto;
	
	private FieldListDTO fieldListDto;
	
	private List<FieldList> list = new ArrayList<>();
	private List<FieldListDTO> listDto = new ArrayList<>();
	
	private void start() {
		FieldList fieldList =  new FieldList(ID, FIELD_NAME, CONTENT, TYPE);
		fieldListDto =  new FieldListDTO(FIELD_NAME, CONTENT, TYPE);
		list.add(fieldList);
		payload = new Payload(ID, FORM_CODE, FILE_NAME, TOKEN, list);
		listDto.add(fieldListDto);
		payloadDto = new PayloadDTO(ID, FORM_CODE, FILE_NAME, TOKEN, listDto);
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}

	@Test
	public void findAll() {
		when(service.findAll()).thenReturn(List.of(payload));
		when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(payloadDto);
		
		ResponseEntity<List<PayloadDTO>> response = controller.findAll();
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(PayloadDTO.class, response.getBody().get(INDEX).getClass());
		
		assertEquals(ID, response.getBody().get(INDEX).getId());
		assertEquals(FORM_CODE, response.getBody().get(INDEX).getFormCode());
		assertEquals(FILE_NAME, response.getBody().get(INDEX).getFileName());
		assertEquals(TOKEN, response.getBody().get(INDEX).getToken());
		assertEquals(listDto, response.getBody().get(INDEX).getFieldList());
		
	}
	
	@Test
	public void findById() {
		when(service.findById(Mockito.anyLong())).thenReturn(payload);
		when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(payloadDto);
		
		ResponseEntity<PayloadDTO> response = controller.findById(ID);
		
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(PayloadDTO.class, response.getBody().getClass());
		
		assertEquals(ID, response.getBody().getId());
		assertEquals(FORM_CODE, response.getBody().getFormCode());
		assertEquals(FILE_NAME, response.getBody().getFileName());
		assertEquals(listDto, response.getBody().getFieldList());
		
	}
	
	@Test
	public void save() {
		when(service.save(Mockito.any())).thenReturn(payload);
		
		ResponseEntity<PayloadDTO> response = controller.save(payloadDto, TOKEN);
		
		assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}
	
	@Test
	public void update() {
		when(service.update(Mockito.any())).thenReturn(payload);
		when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(payloadDto);
		
		ResponseEntity<PayloadDTO> response = controller.update(payloadDto, ID, TOKEN);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(PayloadDTO.class, response.getBody().getClass());
		
		assertEquals(ID, response.getBody().getId());
		assertEquals(FORM_CODE, response.getBody().getFormCode());
		assertEquals(FILE_NAME, response.getBody().getFileName());
		assertEquals(listDto, response.getBody().getFieldList());
		
	}
	
	@Test
	public void delete() {
		doNothing().when(service).delete(Mockito.anyLong());
		
		ResponseEntity<Void> response = controller.delete(ID, TOKEN);
		
		assertNotNull(response);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		
		verify(service, times(1)).delete(Mockito.anyLong());
	}
 
}

package com.poc.payload.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.poc.payload.domain.FieldList;
import com.poc.payload.domain.Payload;
import com.poc.payload.domain.dto.FieldListDTO;
import com.poc.payload.domain.dto.PayloadDTO;
import com.poc.payload.repositories.PayloadRepository;

@SpringBootTest
class PayloadServiceImplTest {
	
	private static final java.lang.String TYPE = "type";

	private static final String FIELD_NAME = "fieldName";

	private static final Integer INDEX = 0;

	private static final String TOKEN = "R2VyYW5kbyB0b2tlbiBTU08=";

	private static final String CONTENT = "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis";

	private static final String FILE_NAME = "arquivo_1";

	private static final String FORM_CODE = "x123";

	private static final Long ID = 1L;

	private static final String NOT_FOUND = "Not found: " + ID;

	@InjectMocks
	private PayloadServiceImpl service;
	
	@Mock
	private PayloadRepository repository;
	
	@Mock
	private ModelMapper mapper;
	
	private Payload payload;
	
	private PayloadDTO payloadDto;

	private Optional<Payload> optionalPayload;
	
	private FieldList fieldList;
	
	private FieldListDTO fieldListDto;
	
	private List<FieldList> list = new ArrayList<>();
	private List<FieldListDTO> listDto = new ArrayList<>();
	
	private void startPayload() {
		FieldList fieldList =  new FieldList(ID, FIELD_NAME, CONTENT, TYPE);
		fieldListDto =  new FieldListDTO(FIELD_NAME, CONTENT, TYPE);
		list.add(fieldList);
		payload = new Payload(ID, FORM_CODE, FILE_NAME, TOKEN, list);
		optionalPayload = Optional.of(new Payload(ID, FORM_CODE, FILE_NAME, TOKEN, list));
		listDto.add(fieldListDto);
		payloadDto = new PayloadDTO(ID, FORM_CODE, FILE_NAME, TOKEN, listDto);
	}
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		startPayload();
	}

	@Test
	public void findAll() {
		when(repository.findAll()).thenReturn(List.of(payload));
		
		List<Payload> response = service.findAll();
		
		assertNotNull(response);
		assertEquals(Payload.class, response.get(INDEX).getClass());	
		
		assertEquals(ID, response.get(INDEX).getId());
		assertEquals(FORM_CODE, response.get(INDEX).getFormCode());
		assertEquals(FILE_NAME, response.get(INDEX).getFileName());
		assertEquals(fieldList, response.get(INDEX).getFieldList());

	}
	
	@Test
	public void findById() {
		when(repository.findById(Mockito.anyLong())).thenReturn(optionalPayload);
		
		Payload response = service.findById(ID);
		
		assertNotNull(response);
		assertEquals(Payload.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(FORM_CODE, response.getFormCode());
		assertEquals(FILE_NAME, response.getFileName());
		assertEquals(fieldList, response.getFieldList());

	}

	@Test
	public void findByIdEntityNotFoundException() {
		when(repository.findById(Mockito.anyLong())).thenThrow(new EntityNotFoundException(NOT_FOUND));

		try {
			service.findById(ID);
		} catch (Exception e) {
			assertEquals(EntityNotFoundException.class, e.getClass());
			assertEquals(NOT_FOUND, e.getMessage());
		}	
		
	}
	
	@Test
	public void save() {
		when(repository.save(Mockito.any())).thenReturn(payload);
		when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(payload);
		
		Payload response = service.save(payloadDto);
		
		assertNotNull(response);
		assertEquals(Payload.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(FORM_CODE, response.getFormCode());
		assertEquals(FILE_NAME, response.getFileName());
		assertEquals(fieldList, response.getFieldList());

	}
	
	@Test
	public void update() {
		when(repository.save(Mockito.any())).thenReturn(payload);
		
		Payload response = service.update(payloadDto);
		
		assertNotNull(response);
		assertEquals(Payload.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(FORM_CODE, response.getFormCode());
		assertEquals(FILE_NAME, response.getFileName());
		assertEquals(fieldList, response.getFieldList());
		
	}
	
	@Test
	public void delete() {
		when(repository.findById(Mockito.anyLong())).thenReturn(optionalPayload);
		doNothing().when(repository).delete(Mockito.any());
		
		service.delete(ID);
		
		verify(repository, times(1)).delete(Mockito.any());
	}
}

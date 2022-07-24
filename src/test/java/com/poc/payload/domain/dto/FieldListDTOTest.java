package com.poc.payload.domain.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FieldListDTOTest {
	
	private static final String TYPE = "type";
	private static final String FIELD_NAME = "fieldName";
	private static final String CONTENT = "content";
	
	@Test
	public void testFieldList() {
		FieldListDTO fieldListDto = new FieldListDTO();
		fieldListDto.setFieldName(FIELD_NAME);
		fieldListDto.setContent(CONTENT);
		fieldListDto.setType(TYPE);
		
		assertNotNull(fieldListDto);
		assertEquals(FIELD_NAME, fieldListDto.getFieldName());
		assertEquals(CONTENT, fieldListDto.getContent());
		assertEquals(TYPE, fieldListDto.getType());
	}

}

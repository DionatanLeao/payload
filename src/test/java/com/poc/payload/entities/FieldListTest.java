package com.poc.payload.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.poc.payload.domain.FieldList;

@SpringBootTest
public class FieldListTest {
	
	private static final String TYPE = "type";
	private static final String FIELD_NAME = "fieldName";
	private static final String CONTENT = "content";
	private static final long ID = 1L;
	
	@Test
	public void testFieldList() {
		FieldList fieldList = new FieldList();
		fieldList.setId(ID);
		fieldList.setFieldName(FIELD_NAME);
		fieldList.setContent(CONTENT);
		fieldList.setType(TYPE);
		
		assertNotNull(fieldList);
		assertEquals(ID, fieldList.getId());
		assertEquals(FIELD_NAME, fieldList.getFieldName());
		assertEquals(CONTENT, fieldList.getContent());
		assertEquals(TYPE, fieldList.getType());
	}

}

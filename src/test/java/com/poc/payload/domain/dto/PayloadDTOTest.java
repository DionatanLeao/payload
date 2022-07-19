package com.poc.payload.domain.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class payloadDtoDTOTest {
	
	private static final String TYPE = "type";
	private static final String FIELD_NAME = "fieldName";
	private static final String TOKEN = "token";
	private static final String CONTENT = "content";
	private static final String FILE_NAME = "arquivo_0";
	private static final String FORM_CODE = "x123";
	private static final long ID = 1L;

	@Test
	void testPayloadDTO() {
		PayloadDTO payloadDto = new PayloadDTO();

		payloadDto.setId(ID);
		payloadDto.setFormCode(FORM_CODE);
		payloadDto.setFileName(FILE_NAME);
		payloadDto.setToken(TOKEN);
		
		FieldListDTO fieldListDto =  new FieldListDTO(FIELD_NAME, CONTENT, TYPE);
		payloadDto.setFieldList(Arrays.asList(fieldListDto));

		assertNotNull(payloadDto);

		assertEquals(ID, payloadDto.getId());
		assertEquals(FORM_CODE, payloadDto.getFormCode());
		assertEquals(FILE_NAME, payloadDto.getFileName());
		assertEquals(TOKEN, payloadDto.getToken());
		assertEquals(CONTENT, payloadDto.getFieldList());
	}

}

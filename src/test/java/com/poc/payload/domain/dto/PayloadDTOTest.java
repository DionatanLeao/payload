package com.poc.payload.domain.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PayloadDTOTest {
	
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
		FieldListDTO fieldListDto = new FieldListDTO(FIELD_NAME, CONTENT, TYPE);
		
		List<FieldListDTO> listFieldList =  new ArrayList<>();
		listFieldList.add(fieldListDto);
		payloadDto.setFieldList(listFieldList);

		assertNotNull(payloadDto);
		assertEquals(ID, payloadDto.getId());
		assertEquals(FORM_CODE, payloadDto.getFormCode());
		assertEquals(FILE_NAME, payloadDto.getFileName());
		assertEquals(TOKEN, payloadDto.getToken());
		assertEquals(listFieldList, payloadDto.getFieldList());
	}

}

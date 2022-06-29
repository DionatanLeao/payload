package com.poc.payload.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.poc.payload.domain.Payload;

@SpringBootTest
class PayloadTest {

	private static final String TOKEN = "token";
	private static final String CONTENT = "content";
	private static final String FILE_NAME = "arquivo_0";
	private static final String FORM_CODE = "x123";
	private static final long ID = 1L;

	@Test
	void test() {
		Payload payload = new Payload();

		payload.setId(ID);
		payload.setFormCode(FORM_CODE);
		payload.setFileName(FILE_NAME);
		payload.setContent(CONTENT);
		payload.setToken(TOKEN);

		assertNotNull(payload);

		assertEquals(ID, payload.getId());
		assertEquals(FORM_CODE, payload.getFormCode());
		assertEquals(FILE_NAME, payload.getFileName());
		assertEquals(CONTENT, payload.getContent());
		assertEquals(TOKEN, payload.getToken());
	}

}

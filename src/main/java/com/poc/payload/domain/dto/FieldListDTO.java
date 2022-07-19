package com.poc.payload.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldListDTO {
	
	private String fieldName;
	private String content;
	private String type;
}

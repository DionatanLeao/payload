package com.poc.payload.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayloadDTO {
	
	private Long id;
	private String formCode;
	private String fileName;
	private String content;
	
	@JsonIgnore
	private String token;
}

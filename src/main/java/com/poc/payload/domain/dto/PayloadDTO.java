package com.poc.payload.domain.dto;

import java.util.ArrayList;
import java.util.List;

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
	
	@JsonIgnore
	private String token;
	
	private List<FieldListDTO> fieldList = new ArrayList<>();
}

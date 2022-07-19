package com.poc.payload.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poc.payload.domain.FieldList;

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
	
	private List<FieldList> fieldList = new ArrayList<>();
}

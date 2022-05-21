package com.poc.payload.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Payload")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayloadDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String formCode;
	private String fileName;
	private String content;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String token;
	
}

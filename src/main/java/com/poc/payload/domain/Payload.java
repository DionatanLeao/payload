package com.poc.payload.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Payload implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String formCode;
	private String fileName;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String token;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<FieldList> fieldList = new ArrayList<>();
	
}

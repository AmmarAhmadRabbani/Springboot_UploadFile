package com.te.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PersonDoc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer docId;
	private String fileName;
	private String url;
}

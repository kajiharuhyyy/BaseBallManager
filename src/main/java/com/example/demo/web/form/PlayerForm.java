package com.example.demo.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PlayerForm {

	private Long id;
	
	@NotBlank(message = "選手を入れてください")
	private String name;
	
	@NotNull(message = "ポジションを選択してください")
	private String position;
	
	@NotBlank(message = "背番号を入れてください")
	private Integer uniformNumber;
}

package com.example.demo.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class PlayerForm {

	private Long id;
	
	@NotBlank(message = "選手を入れてください")
	@Size(max = 50)
	private String name;
	
	@NotNull(message = "ポジションを選択してください")
	@Size(max = 20)
	private String position;
	
	@NotNull
	private Integer uniformNumber;
}

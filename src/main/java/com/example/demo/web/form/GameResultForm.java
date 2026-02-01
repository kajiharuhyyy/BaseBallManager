package com.example.demo.web.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GameResultForm {

	private Long id;

	@NotNull
	@Min(0)
	private Integer myScore;

	@NotNull
	@Min(0)
	private Integer opponentScore;
}

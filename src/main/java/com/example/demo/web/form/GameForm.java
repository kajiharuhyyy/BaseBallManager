package com.example.demo.web.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GameForm {

	private Long id;

	@NotNull
	private LocalDate gameDate;

	private String opponent;

	private Integer myScore;

	private Integer opponentScore;
}

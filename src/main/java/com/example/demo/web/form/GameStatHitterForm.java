package com.example.demo.web.form;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GameStatHitterForm {

	private Long id;

	@NotNull
	private Long gameId;

	@NotNull
	private Long playerId;

	@NotNull
	private Integer atBats;

	@NotNull
	private Integer hits;

	@NotNull
	private Integer homeRuns;

	@NotNull
	private Integer walks;

	@NotNull
	private Integer stolenBases;
}

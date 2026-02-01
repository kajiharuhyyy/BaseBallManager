package com.example.demo.web.form;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import com.example.demo.domain.Wins;

import lombok.Data;

@Data
public class GameStatPitcherForm {

	private Long id;

	@NotNull
	private Long gameId;

	@NotNull
	private Long playerId;

	@NotNull
	@DecimalMin("0.0")
	private BigDecimal inningsPitched;

	@NotNull
	@Min(0)
	private Integer runsAllowed;

	@NotNull
	private Wins wins;
}

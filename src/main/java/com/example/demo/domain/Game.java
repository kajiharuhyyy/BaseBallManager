package com.example.demo.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "game_date", nullable = false)
	private LocalDate gameDate;
	
	@Column(nullable = true)
	private String opponent;

	@Column(name = "start_time", nullable = true)
	private LocalTime startTime;

	@Column(nullable = true)
	private String ground;
	
	@Column(name = "my_score", nullable = true)
	private Integer myScore;
	
	@Column(name = "opponent_score", nullable = true)
	private Integer opponentScore;

	@Builder
	public Game(LocalDate gameDate, String opponent, LocalTime startTime, String ground,
			Integer myScore, Integer opponentScore) {
		this.gameDate = gameDate;
		this.opponent = opponent;
		this.startTime = startTime;
		this.ground = ground;
		this.myScore = myScore;
		this.opponentScore = opponentScore;
	}
}

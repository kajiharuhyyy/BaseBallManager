package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "game_stats")
@Getter
@Setter
@NoArgsConstructor
public class GameStat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Id
	private Long gameId;
	
	@Id
	private Long playerId;
	
	@Column(name = "at_bats", nullable = false)
	private Integer atBats;
	
	@Column(nullable = false)
	private Integer hits;
	
	@Column(name = "home_runs", nullable = false)
	private Integer homeRuns;
	
	@Column(nullable = false)
	private Integer walks;
	
	@Column(name = "stolen_bases", nullable = false)
	private Integer stolenBases;
	
	@Column(name = "innings_pitched", nullable = false)
	private Integer inningsPitched;
	
	@Column(name = "runs_allowed", nullable = false)
	private Integer runsAllowed;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private  Wins wins;
	
	@Builder
	public GameStat(Long id, Long gameId , Long playerId, Integer atBats, Integer hits, 
			Integer homeRuns, Integer walks, Integer stolenBases, 
			Integer inningsPitched, Integer runsAllowed, Wins wins) {
		
		this.atBats = atBats;
		this.hits = hits;
		this.homeRuns = homeRuns;
		this.walks = walks;
		this.stolenBases = stolenBases;
		this.inningsPitched = inningsPitched;
		this.runsAllowed = runsAllowed;
		this.wins = wins;
	}
	
}
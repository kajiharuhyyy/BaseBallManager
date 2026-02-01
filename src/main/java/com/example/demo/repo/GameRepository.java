package com.example.demo.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

	List<Game> findByGameDate(LocalDate gameDate);

	List<Game> findByMyScore(int myScore);

	List<Game> findByOpponent(String opponent);

	List<Game> findByOpponentScore(int opponentScore);
}

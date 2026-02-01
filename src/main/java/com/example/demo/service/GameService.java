package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Game;
import com.example.demo.repo.GameRepository;
import com.example.demo.web.form.GameForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameService {

	private final GameRepository gameRepository;
	
	public List<Game> search(LocalDate gameDate, String opponent, LocalTime startTime, String ground, Integer myScore, Integer opponentScore) {
	    return gameRepository.findAll().stream()
	        .filter(g -> gameDate == null || gameDate.equals(g.getGameDate()))
	        .filter(g -> opponent == null || opponent.isBlank() || (g.getOpponent() != null && g.getOpponent().contains(opponent.trim())))
	        .filter(g -> startTime == null || startTime.equals(g.getStartTime()))
	        .filter(g -> ground == null || ground.isBlank() || (g.getGround() != null && g.getGround().contains(ground.trim())))
	        .filter(g -> myScore == null || (g.getMyScore() != null && g.getMyScore().equals(myScore)))
	        .filter(g -> opponentScore == null || (g.getOpponentScore() != null && g.getOpponentScore().equals(opponentScore)))
	        .toList();
	}

	
	/**
	 * 登録処理
	 * @param form
	 * @return　保存
	 */
	@Transactional
	public Game create(GameForm form) {
		Game game = Game.builder()
				.gameDate(form.getGameDate())
				.opponent(form.getOpponent())
				.startTime(form.getStartTime())
				.ground(form.getGround())
				.build();
		
		return gameRepository.save(game);
	}
	
	public Game getById(Long id) {
		return gameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid game ID: " + id));
	}
	
	@Transactional
	public Game update(Long id, GameForm form) {
		Game game = getById(id);
		
		game.setGameDate(form.getGameDate());
		game.setOpponent(form.getOpponent());
		game.setStartTime(form.getStartTime());
		game.setGround(form.getGround());
		
		return game;
	}
	
	@Transactional
	public Game updateResult(Long id, Integer myScore, Integer opponentScore) {
		Game game = getById(id);
		
		game.setMyScore(myScore);
		game.setOpponentScore(opponentScore);
		
		return game;
	}
	
	@Transactional
	public void delete(Long id) {
		gameRepository.deleteById(id);
	}
}

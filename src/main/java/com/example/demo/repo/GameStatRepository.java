package com.example.demo.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.GameStat;
import com.example.demo.domain.Wins;

public interface GameStatRepository extends JpaRepository<GameStat, Long> {

	List<GameStat> findByGameId(Long gameId);

	List<GameStat> findByPlayerId(Long playerId);

	List<GameStat> findByAtBats(Integer atBats);

	List<GameStat> findByHits(Integer hits);

	List<GameStat> findByHomeRuns(Integer homeRuns);

	List<GameStat> findByWalks(Integer walks);

	List<GameStat> findByStolenBases(Integer stolenBases);

	List<GameStat> findByInningsPitched(BigDecimal inningsPitched);

	List<GameStat> findByRunsAllowed(Integer runsAllowed);

	List<GameStat> findByWins(Wins wins);
}

package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.GameStat;
import com.example.demo.domain.Wins;
import com.example.demo.repo.GameStatRepository;
import com.example.demo.web.form.GameStatHitterForm;
import com.example.demo.web.form.GameStatPitcherForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameStatService {

    private final GameStatRepository gameStatRepository;

    public List<GameStat> search(
            Long gameId,
            Long playerId,
            Integer atBats,
            Integer hits,
            Integer homeRuns,
            Integer walks,
            Integer stolenBases,
            BigDecimal inningsPitched,
            Integer runsAllowed,
            Wins wins) {

        return gameStatRepository.findAll().stream()
            .filter(gs -> gameId == null || gameId.equals(gs.getGameId()))
            .filter(gs -> playerId == null || playerId.equals(gs.getPlayerId()))
            .filter(gs -> atBats == null || atBats.equals(gs.getAtBats()))
            .filter(gs -> hits == null || hits.equals(gs.getHits()))
            .filter(gs -> homeRuns == null || homeRuns.equals(gs.getHomeRuns()))
            .filter(gs -> walks == null || walks.equals(gs.getWalks()))
            .filter(gs -> stolenBases == null || stolenBases.equals(gs.getStolenBases()))
            .filter(gs -> inningsPitched == null || inningsPitched.equals(gs.getInningsPitched()))
            .filter(gs -> runsAllowed == null || runsAllowed.equals(gs.getRunsAllowed()))
            .filter(gs -> wins == null || wins.equals(gs.getWins()))
            .toList();
    }

    // 打者成績の登録
    @Transactional
    public GameStat createHitter(GameStatHitterForm form) {
        GameStat gameStat = GameStat.builder()
	            .gameId(form.getGameId())
	            .playerId(form.getPlayerId())
	            .atBats(form.getAtBats())
	            .hits(form.getHits())
	            .homeRuns(form.getHomeRuns())
	            .walks(form.getWalks())
	            .stolenBases(form.getStolenBases())
	            .inningsPitched(BigDecimal.ZERO)
	            .runsAllowed(0)
	            .wins(Wins.NONE)
            .build();

        return gameStatRepository.save(gameStat);
    }

    // 投手成績の登録
    @Transactional
    public GameStat createPitcher(GameStatPitcherForm form) {
        GameStat gameStat = GameStat.builder()
            .gameId(form.getGameId())
            .playerId(form.getPlayerId())
            .atBats(0)
            .hits(0)
            .homeRuns(0)
            .walks(0)
            .stolenBases(0)
            .inningsPitched(form.getInningsPitched())
            .runsAllowed(form.getRunsAllowed())
            .wins(form.getWins())
            .build();

        return gameStatRepository.save(gameStat);
    }

    public GameStat getById(Long id) {
        return gameStatRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid gameStat ID: " + id));
    }

    // 打者成績の更新
    @Transactional
    public GameStat updateHitter(Long id, GameStatHitterForm form) {
        GameStat gameStat = getById(id);

        gameStat.setGameId(form.getGameId());
        gameStat.setPlayerId(form.getPlayerId());
        gameStat.setAtBats(form.getAtBats());
        gameStat.setHits(form.getHits());
        gameStat.setHomeRuns(form.getHomeRuns());
        gameStat.setWalks(form.getWalks());
        gameStat.setStolenBases(form.getStolenBases());

        return gameStat;
    }

    // 投手成績の更新
    @Transactional
    public GameStat updatePitcher(Long id, GameStatPitcherForm form) {
        GameStat gameStat = getById(id);

        gameStat.setGameId(form.getGameId());
        gameStat.setPlayerId(form.getPlayerId());
        gameStat.setInningsPitched(form.getInningsPitched());
        gameStat.setRunsAllowed(form.getRunsAllowed());
        gameStat.setWins(form.getWins());

        return gameStat;
    }

    @Transactional
    public void delete(Long id) {
        gameStatRepository.deleteById(id);
    }
}

package com.example.demo.web;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.GameStat;
import com.example.demo.domain.Wins;
import com.example.demo.service.GameStatService;
import com.example.demo.web.form.GameStatHitterForm;
import com.example.demo.web.form.GameStatPitcherForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GameStatController {
	
	private final GameStatService gameStatService;
	
	@GetMapping("/gamestats")
	public String listStat(
			@RequestParam(required = false) Long gameId,
			@RequestParam(required = false) Long playerId,
			@RequestParam(required = false) Integer atBats,
			@RequestParam(required = false) Integer hits,
			@RequestParam(required = false) Integer homeRuns,
			@RequestParam(required = false) Integer walks,
			@RequestParam(required = false) Integer stolenBases,
			@RequestParam(required = false) BigDecimal inningsPitched,
			@RequestParam(required = false) Integer runsAllowed,
			@RequestParam(required = false) Wins wins,
			Model model) {
		
		List<GameStat> gamestatus = gameStatService.search(gameId, playerId, atBats, 
				hits, homeRuns, walks, stolenBases, inningsPitched, runsAllowed, wins);
		
		model.addAttribute("gamestatus", gamestatus);
		return "gamestats/list";
				
	}
	
	@GetMapping("/gamestats/hitters/new")
	public String newHitStatus(Model model) {
		GameStatHitterForm hForm = new GameStatHitterForm();
		
		model.addAttribute("gameStatHitterForm", hForm);
		return "gamestats/hitters/new";
	}
	
	@GetMapping("/gamestats/pitchers/new")
	public String newPitchStatus(Model model) {
		GameStatPitcherForm pForm = new GameStatPitcherForm();
		
		model.addAttribute("gameStatPitcherForm", pForm);
		return "gamestats/pitchers/new";
	}
	
	@PostMapping("/gamestats/hitters")
	public String createHitStat(
			@Valid @ModelAttribute GameStatHitterForm hForm,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("gameStatHitterForm", hForm);
			return "gamestats/hitters/new"; 
		}
		
		gameStatService.createHitter(hForm);
		return "redirect:/gamestats";
	}
	@PostMapping("/gamestats/pitchers")
	public String createPitchStat(
			@Valid @ModelAttribute GameStatPitcherForm pForm,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("gameStatPitcherForm", pForm);
			return "gamestats/pitchers/new"; 
		}
		
		gameStatService.createPitcher(pForm);
		return "redirect:/gamestats";
	}
	
	@GetMapping("/gamestats/hitters/{id}/edit")
    public String editHitStat(@PathVariable Long id,
            Model model) {
        GameStat gameStatusHitter = gameStatService.getById(id);

        GameStatHitterForm hForm = new GameStatHitterForm();
        hForm.setId(gameStatusHitter.getId());
        hForm.setGameId(gameStatusHitter.getGameId());
        hForm.setPlayerId(gameStatusHitter.getPlayerId()); 
        hForm.setAtBats(gameStatusHitter.getAtBats());
        hForm.setHits(gameStatusHitter.getHits());
        hForm.setHomeRuns(gameStatusHitter.getHomeRuns());
        hForm.setWalks(gameStatusHitter.getWalks());
        hForm.setStolenBases(gameStatusHitter.getStolenBases());

        model.addAttribute("gameStatHitterForm", hForm);
        return "gamestats/hitters/edit";
    }
	
	@GetMapping("/gamestats/pitchers/{id}/edit")
    public String editPitchStat(@PathVariable Long id,
            Model model) {
        GameStat gameStatusPitcher = gameStatService.getById(id);

        GameStatPitcherForm pForm = new GameStatPitcherForm();
        pForm.setId(gameStatusPitcher.getId());
        pForm.setGameId(gameStatusPitcher.getGameId());
        pForm.setPlayerId(gameStatusPitcher.getPlayerId()); 
        pForm.setInningsPitched(gameStatusPitcher.getInningsPitched());
        pForm.setRunsAllowed(gameStatusPitcher.getRunsAllowed());
        pForm.setWins(gameStatusPitcher.getWins());

        model.addAttribute("gameStatPitcherForm", pForm);
        return "gamestats/pitchers/edit";
    }

    @PostMapping("/gamestats/hitters/{id}/edit")
    public String updateHitStat(@PathVariable Long id, 
            @Valid @ModelAttribute GameStatHitterForm hForm, 
            BindingResult bindingResult, 
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("gameStatHitterForm", hForm);
            return "gamestats/hitters/edit";
        }

        gameStatService.updateHitter(id, hForm);
        return "redirect:/gamestats"; 
    }
    
    @PostMapping("/gamestats/pitchers/{id}/edit")
    public String updateGame(@PathVariable Long id, 
            @Valid @ModelAttribute GameStatPitcherForm pForm, 
            BindingResult bindingResult, 
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("gameStatPitcherForm", pForm);
            return "gamestats/pitchers/edit";
        }

        gameStatService.updatePitcher(id, pForm);
        return "redirect:/gamestats"; 
    }

    @PostMapping("/gamestats/{id}/delete")
    public String deleteStat(@PathVariable Long id) {
    	gameStatService.delete(id);
        return "redirect:/gamestats"; 
    }
	

}

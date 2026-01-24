package com.example.demo.web;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		return "gamestatus/list";
				
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
		}
		
		gameStatService.createPitcher(pForm);
		return "redirect:/gamestats";
	}
	

}

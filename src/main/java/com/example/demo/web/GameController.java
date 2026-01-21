package com.example.demo.web;

import java.time.LocalDate;
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

import com.example.demo.domain.Game;
import com.example.demo.service.GameService;
import com.example.demo.web.form.GameForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GameController {
	
	private final GameService gameService;
	
	@GetMapping("/games")
	public String listGame(
			@RequestParam(required = false) LocalDate gameDate,
			@RequestParam(required = false) String opponent,
			@RequestParam(required = false) Integer myScore,
			@RequestParam(required = false) Integer opponentScore,
			Model model) {
		
		List<Game> games = gameService.search(gameDate, opponent, myScore, opponentScore);
		
		model.addAttribute("games", games);
		return "games/list";
	}
	
	@GetMapping("/games/new")
	public String newGame(Model model) {
		GameForm form = new GameForm();
		
		model.addAttribute("gameForm", form);
		return "games/new";
	}
	
	@PostMapping("/games")
	public String createGame(
			@Valid @ModelAttribute GameForm form,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("gameForm", form);
			return "games/new";
		}
		
		gameService.create(form);
		return "redirect:/games";
	}
	
	@GetMapping("/games/{id}/edit")
    public String editGame(@PathVariable Long id,
            Model model) {
        Game game = gameService.getById(id);

        GameForm form = new GameForm();
        form.setId(game.getId());
        form.setGameDate(game.getGameDate());
        form.setOpponent(game.getOpponent()); 
        form.setMyScore(game.getMyScore());
        form.setOpponentScore(game.getOpponentScore());

        model.addAttribute("gameForm", form);
        return "games/edit";
    }

    @PostMapping("/games/{id}/edit")
    public String updateGame(@PathVariable Long id, 
            @Valid @ModelAttribute GameForm form, 
            BindingResult bindingResult, 
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("gameForm", form);
            return "games/edit";
        }

        gameService.update(id, form);
        return "redirect:/games"; 
    }

    @PostMapping("/games/{id}/delete")
    public String deleteGame(@PathVariable Long id) {
    	gameService.delete(id);
        return "redirect:/games"; 
    }

}

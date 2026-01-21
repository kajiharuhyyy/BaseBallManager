package com.example.demo.web;

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

import com.example.demo.domain.Player;
import com.example.demo.service.PlayerService;
import com.example.demo.web.form.PlayerForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PlayerController {
	
	private final PlayerService playerService;
	
	@GetMapping("/players")
	public String listTeamMember(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String position,
			@RequestParam(required = false) Integer uniformNumber,
			Model model) {
		
		List<Player> players = playerService.search(name, position, uniformNumber);
		
		model.addAttribute("players", players);
		return "players/list";
	}
	
	@GetMapping("/players/new")
	public String newTeamMember(Model model) {
		PlayerForm form = new PlayerForm();
		
		model.addAttribute("playerForm", form);
		return "players/new";
	}
	
	@PostMapping("/players")
	public String createTeamMember(
			@Valid @ModelAttribute PlayerForm form,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("playerForm", form);
			return "players/new";
		}
		
		playerService.create(form);
		return "redirect:/players";
	}
	
	@GetMapping("/players/{id}/edit")
    public String editTeamMember(@PathVariable Long id,
            Model model) {
        Player player = playerService.getById(id);

        PlayerForm form = new PlayerForm();
        form.setId(player.getId());
        form.setName(player.getName());
        form.setPosition(player.getPosition()); 
        form.setUniformNumber(player.getUniformNumber());

        model.addAttribute("playerForm", form);
        return "players/edit";
    }

    @PostMapping("/players/{id}/edit")
    public String updateTeamMember(@PathVariable Long id, 
            @Valid @ModelAttribute PlayerForm form, 
            BindingResult bindingResult, 
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("playerForm", form);
            return "players/edit";
        }

        playerService.update(id, form);
        return "redirect:/players"; 
    }

    @PostMapping("/players/{id}/delete")
    public String deleteTeamMember(@PathVariable Long id) {
        playerService.delete(id);
        return "redirect:/players"; 
    }

}	

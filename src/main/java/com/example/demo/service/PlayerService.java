package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Player;
import com.example.demo.repo.PlayerRepository;
import com.example.demo.web.form.PlayerForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerService {

	private final PlayerRepository playerRepository; 
	
	public List<Player> search(String name, String position, Integer uniformNumber) {
		
		return playerRepository.findAll().stream()
				.filter(p -> name == null || name.isBlank() || p.getName().contains(name.trim()))
				.filter(p -> position == null || position.isBlank() || p.getPosition().contains(position.trim()))
				.filter(p -> uniformNumber == null || p.getUniformNumber().equals(uniformNumber))
				.toList();
		
//		List<Player> players = playerRepository.findAll();
		
//		// 何も指定がなければ全件返す
//		if ((name != null || name.isBlank()) 
//				&& (position == null || position.isBlank()) 
//				&& uniformNumber == null) {
//			return playerRepository.findAll();
//		}
//		
//		// 背番号が入ってるならそれで検索する
//		if (uniformNumber != null) {
//			return playerRepository.findByUniformNumber(uniformNumber);
//		}
//		
//		// 名前検索
//		if (name!= null && !name.isBlank()) {
//			return playerRepository.findByName(name);
//		}
//		
//		// ポジション検索
//		if (position != null && !position.isBlank()) {
//			return playerRepository.findByPosition(position);
//		}
		
//		return playerRepository.findAll();
	}
	
	/**
	 * 登録処理
	 * @param form
	 * @return　保存
	 */
	@Transactional
	public Player create(PlayerForm form) {
		Player player = Player.builder()
				.name(form.getName())
				.position(form.getPosition())
				.uniformNumber(form.getUniformNumber())
				.build();
		
		return playerRepository.save(player);
	}
	
	public Player getById(Long id) {
		return playerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid player ID: " + id));
	}
	
	@Transactional
	public Player update(Long id, PlayerForm form) {
		Player player = getById(id);
		
		player.setName(form.getName());
		player.setPosition(form.getPosition());
		player.setUniformNumber(form.getUniformNumber());
		
		return player;
	}
	
	@Transactional
	public void delete(Long id) {
		playerRepository.deleteById(id);
	}
}

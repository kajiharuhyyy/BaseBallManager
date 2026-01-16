package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Player;

/**
 * Repository
 * DB操作をJavaメソッドとして安全に簡単に使うためのコード
 * データ取得のためのルールの宣言をしている。
 */
public interface PlayerRepository extends JpaRepository<Player, Long>{
	
	
	List<Player> findByName(String name);
	
	List<Player> findByPosition(String position);
	
	List<Player> findByUniformNumber(Integer uniformNumber);

}

CREATE TABLE players (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  position VARCHAR(20) NOT NULL,
  uniform_number INT NOT NULL
);

CREATE TABLE games (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  game_date DATE NOT NULL,
  opponent VARCHAR(50),
  ground VARCHAR(50),
  start_time TIME NOT NULL
);

CREATE TABLE game_stats (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  game_id BIGINT NOT NULL,
  player_id BIGINT NOT NULL,
  at_bats INT DEFAULT 0,
  hits INT DEFAULT 0,
  home_runs INT DEFAULT 0,
  walks INT DEFAULT 0,
  stolen_bases INT DEFAULT 0,
  innings_pitched DECIMAL(4,1),
  runs_allowed INT,
  wins VARCHAR(10),
  FOREIGN KEY (game_id) REFERENCES games(id),
  FOREIGN KEY (player_id) REFERENCES players(id)
);

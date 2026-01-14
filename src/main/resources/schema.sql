CREATE TABLE players (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, --id
    name VARCHAR(50), -- 名前
    position VARCHAR(20) NOT NULL, -- ポジション
    uniform_number INT NOT NULL, -- 背番号
);

CREATE TABLE games (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_date DATE NOT NULL,        -- 試合日
    opponent VARCHAR(50),           -- 対戦相手
    my_score INT,
    opponent_score INT
);

CREATE TABLE game_stats (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT NOT NULL,
    player_id BIGINT NOT NULL,

    at_bats INT DEFAULT 0,       -- 打数
    hits INT DEFAULT 0,          -- 安打
    home_runs INT DEFAULT 0,     -- 本塁打
    walks INT DEFAULT 0,         -- 四球
    stolen_bases INT DEFAULT 0,  -- 盗塁

    innings_pitched DECIMAL(4,1), -- 投球回
    runs_allowed INT,             -- 失点
    wins BOOLEAN DEFAULT FALSE,   -- 勝ち投手か

    FOREIGN KEY (game_id) REFERENCES games(id),
    FOREIGN KEY (player_id) REFERENCES players(id)
);

-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS tennis_tournament;

-- Use the database
USE tennis_tournament;

-- Drop existing tables if they exist
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS tournaments;
DROP TABLE IF EXISTS matches;
DROP TABLE IF EXISTS user_tournaments;
DROP TABLE IF EXISTS referee_schedule;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role ENUM('PLAYER', 'REFEREE', 'ADMIN') NOT NULL
);

CREATE TABLE tournaments (
    tournament_id INT AUTO_INCREMENT PRIMARY KEY,
    tournament_name VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

CREATE TABLE matches (
    match_id INT AUTO_INCREMENT PRIMARY KEY,
    tournament_id INT,
    player1_id INT NOT NULL,
    player2_id INT NOT NULL,
    referee_id INT,
    match_date DATETIME NOT NULL,
    match_score VARCHAR(20),
    FOREIGN KEY (tournament_id) REFERENCES tournaments(tournament_id),
    FOREIGN KEY (player1_id) REFERENCES users(user_id),
    FOREIGN KEY (player2_id) REFERENCES users(user_id),
    FOREIGN KEY (referee_id) REFERENCES users(user_id)
);

CREATE TABLE user_tournaments (
    user_tournament_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    tournament_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (tournament_id) REFERENCES tournaments(tournament_id)
);

CREATE TABLE referee_schedule (
    referee_schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    referee_id INT,
    match_id INT,
    FOREIGN KEY (referee_id) REFERENCES users(user_id),
    FOREIGN KEY (match_id) REFERENCES matches(match_id)
);

-- Populate users table
INSERT INTO users (username, password, email, role) VALUES
('johnD', 'hashedPass1', 'john@example.com', 'PLAYER'),
('aliceS', 'hashedPass2', 'alice@example.com', 'REFEREE'),
('admin1', 'hashedPass3', 'admin@example.com', 'ADMIN'),
('sarahT', 'hashedPass4', 'sarah@example.com', 'PLAYER'),
('referee2', 'hashedPass5', 'referee2@example.com', 'REFEREE');
INSERT INTO users (username, password, email, role) VALUES
('user1', 'user1', 'test@test.com', 'PLAYER');
INSERT INTO users (username, password, email, role) VALUES
('ref1', 'ref1', 'test1@test.com', 'REFEREE'),
('admin2', 'admin2', 'test2@test.com', 'ADMIN');


-- Populate tournaments table
INSERT INTO tournaments (tournament_name, start_date, end_date) VALUES
('Australian Open', '2024-01-01', '2024-01-14'),
('French Open', '2024-05-26', '2024-06-09'),
('Wimbledon', '2024-07-01', '2024-07-14'),
('US Open', '2024-08-26', '2024-09-08'),
('ATP Finals', '2024-11-10', '2024-11-17');

-- Populate matches table
INSERT INTO matches (tournament_id, player1_id, player2_id, referee_id, match_date, match_score) VALUES
(1, 1, 4, 2, '2024-01-05 10:00:00', '6-4, 6-3'),
(1, 3, 5, 2, '2024-01-06 13:00:00', '6-2, 6-1'),
(2, 1, 5, 3, '2024-05-28 11:00:00', '6-3, 7-5'),
(3, 2, 4, 4, '2024-07-05 14:00:00', '7-5, 6-4'),
(4, 3, 4, 5, '2024-08-30 12:00:00', '6-4, 6-2');

-- Populate user_tournaments table
INSERT INTO user_tournaments (user_id, tournament_id) VALUES
(1, 1),
(1, 3),
(2, 2),
(3, 4),
(4, 1);

-- Populate referee_schedule table
INSERT INTO referee_schedule (referee_id, match_id) VALUES
(2, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);


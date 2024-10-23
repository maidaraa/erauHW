-- ------------------------------------------------
-- Create the Tables
use Legos;

CREATE TABLE LegoSets(
	itemNumber INT PRIMARY KEY, 
	name VARCHAR(60), 
	theme VARCHAR(60),
	year INT, 
	ageRange VARCHAR(10),
	piecesNum INT, 
	retired BOOLEAN DEFAULT FALSE);

CREATE TABLE Themes(
	name VARCHAR(60) PRIMARY KEY,
	films BOOLEAN DEFAULT FALSE,
	videoGames BOOLEAN DEFAULT FALSE);

CREATE TABLE LegoKeychains(
	itemNumber INT PRIMARY KEY, 
	name VARCHAR(60), 
	theme VARCHAR(60),
	year INT);	

ALTER TABLE LegoSets
ADD CONSTRAINT chk_year_range
CHECK (year >= 1932);	

ALTER TABLE LegoKeychains
ADD CONSTRAINT chk_year_range
CHECK (year >= 1978);	

INSERT INTO LegoSets VALUES 
(76908, 'Lamborghini Countach', 'Speed Champions', 2022, '8+', 262, FALSE),
(76914, 'Ferrari 812 Competizione', 'Speed Champions', 2023, '9+', 261, FALSE),
(10328, 'Bouquet of Roses', 'Botanical Collection', 2024, '18+', 822, FALSE), 
(75335, 'BD-1™', 'Star Wars', 2022, '14+', 1062, TRUE),
(40441, 'Shorthair Cats', 'Brickheadz', 2022, '8+', 250, TRUE);

INSERT INTO Themes VALUES 
('Speed Champions', FALSE, FALSE),
('Spider-Man', TRUE, TRUE),
('Botanical Collection', FALSE, FALSE), 
('Star Wars', TRUE, TRUE),
('Brickheadz', FALSE, FALSE);

INSERT INTO LegoKeychains VALUES 
(854153, 'Miles Morales Key Chain', 'Spider-Man', 2022),
(854235, 'Batman™ Key Chain', 'Batman', 2023),
(854236, 'Darth Vader Key Chain', 'Star Wars', 2023), 
(854294, 'Loki Key Chain', 'Marvel', 2024),
(854290, 'Spider-Man Key Chain', 'Spider-Man', 2024);





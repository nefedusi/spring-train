--DROP TABLE IF EXISTS thing;

CREATE TABLE IF NOT EXISTS thing (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(200) DEFAULT NULL
);

INSERT INTO thing (id, name) VALUES
(1, 'Spade'), (2, 'Hammer'), (3, 'Axe');

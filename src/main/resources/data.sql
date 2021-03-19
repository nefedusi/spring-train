--DROP TABLE IF EXISTS thing;
--DROP TABLE IF EXISTS person;
--DROP TABLE IF EXISTS person_role;

CREATE TABLE IF NOT EXISTS thing (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(200) DEFAULT NULL
);

INSERT INTO thing (id, name) VALUES
(1, 'Spade'), (2, 'Hammer'), (3, 'Axe');

CREATE TABLE IF NOT EXISTS person (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL
);

INSERT INTO person (id, login, password) VALUES
(1, 'user', '{noop}userpass'), (2, 'admin', '{noop}adminpass'), (3, 'cleanadmin', '{noop}cleanadminpass');

CREATE TABLE IF NOT EXISTS person_role (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  person_id BIGINT NOT NULL,
  role VARCHAR(50) NOT NULL,
  FOREIGN KEY(person_id) REFERENCES person(id)
);

-- 0 - ROLE_USER
-- 1 - ROLE_ADMIN
INSERT INTO person_role (id, person_id, role) VALUES
(1, 1, 0), (2, 2, 0), (3, 2, 1), (4, 3, 1);


CREATE TABLE IF NOT EXISTS parent_entity (
  id VARCHAR(255) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
);

--INSERT INTO parent_entity (id, name) VALUES
--(1, 'P1'), (2, 'P2');

CREATE TABLE IF NOT EXISTS child_entity (
  id VARCHAR(255) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  parent_entity_id VARCHAR(255)--,
  --FOREIGN KEY(parent_entity_id) REFERENCES parent_entity(id)
);

--INSERT INTO child_entity (id, name, parent_entity_id) VALUES
--(1, 'C1', 1), (2, 'C2', 1), (3, 'C3', 1), (4, 'C4', 2);
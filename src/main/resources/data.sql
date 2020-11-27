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

INSERT INTO person_role (id, person_id, role) VALUES
(1, 1, 'ROLE_USER'), (2, 2, 'ROLE_USER'), (3, 2, 'ROLE_ADMIN'), (4, 3, 'ROLE_ADMIN');

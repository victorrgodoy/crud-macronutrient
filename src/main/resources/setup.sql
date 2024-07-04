-- Script SQL para criar tabelas no banco de dados

-- Tabela 'user' para armazenar informações dos usuários
CREATE TABLE user (
  user_id int NOT NULL AUTO_INCREMENT,
  cpf varchar(11) NOT NULL,
  name varchar(45) NOT NULL,
  age int NOT NULL,
  weight double NOT NULL,
  height double NOT NULL,
  gender enum('MALE', 'FEMALE') NOT NULL,
  objective enum('BULKING', 'CUTTING') NOT NULL,
  activity_level varchar(45) NOT NULL,
  PRIMARY KEY (user_id),
  UNIQUE KEY cpf_UNIQUE (cpf)
);

-- Tabela 'historic' para armazenar históricos de IMC dos usuários
CREATE TABLE historic (
  historic_id int NOT NULL AUTO_INCREMENT,
  user_id int NOT NULL,
  weight double NOT NULL,
  imc double NOT NULL,
  type_imc varchar(45) NOT NULL,
  date date NOT NULL,
  PRIMARY KEY (historic_id),
  FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabela 'diet' para armazenar informações de dieta dos usuários
CREATE TABLE diet (
  diet_id int NOT NULL AUTO_INCREMENT,
  user_id int NOT NULL,
  cal double NOT NULL,
  protein double NOT NULL,
  carb double NOT NULL,
  fat double NOT NULL,
  date date NOT NULL,
  PRIMARY KEY (diet_id),
  FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

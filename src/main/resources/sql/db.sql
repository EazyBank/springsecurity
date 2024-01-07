CREATE DATABASE IF NOT EXISTS easybank;

USE easybank;

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id       INT         NOT NULL AUTO_INCREMENT,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(250) NOT NULL,
    enabled  INT         NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS authorities;

CREATE TABLE authorities
(
    id        INT         NOT NULL AUTO_INCREMENT,
    username  VARCHAR(45) NOT NULL,
    authority VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

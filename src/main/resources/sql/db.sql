CREATE DATABASE IF NOT EXISTS easybank;

USE easybank;

DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers
(
    id       INT          NOT NULL AUTO_INCREMENT,
    email    VARCHAR(45)  NOT NULL,
    password VARCHAR(250) NOT NULL,
    role     VARCHAR(45)  NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS authorities;

# CREATE TABLE authorities
# (
#     id        INT         NOT NULL AUTO_INCREMENT,
#     username  VARCHAR(45) NOT NULL,
#     authority VARCHAR(45) NOT NULL,
#     PRIMARY KEY (id)
# );

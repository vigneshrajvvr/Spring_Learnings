CREATE DATABASE mydb;

USE mydb;

CREATE TABLE Product(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(20),
description VARCHAR(20),
price INT
);
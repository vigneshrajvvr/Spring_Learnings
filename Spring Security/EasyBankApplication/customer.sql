USE easybank;

CREATE TABLE customer(
id INT NOT NULL auto_increment,
email VARCHAR(45) NOT NULL,
password VARCHAR(45) NOT NULL,
role VARCHAR(45) NOT NULL,
PRIMARY KEY (id)
);

INSERT INTO customer VALUES (NULL, 'test1.example.com', '12345', 'admin');
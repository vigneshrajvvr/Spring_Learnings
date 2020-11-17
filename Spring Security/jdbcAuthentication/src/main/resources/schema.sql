DROP TABLE IF EXISTS users;

CREATE TABLE users(
	username VARCHAR(50) NOT NULL PRIMARY KEY,
	password VARCHAR(500) NOT NULL,
	enabled BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS authorities;

CREATE TABLE authorities (
	username VARCHAR(255) NOT NULL,
	authority VARCHAR(50) NOT NULL,
	constraint fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);
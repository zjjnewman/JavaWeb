
DROP TABLE IF EXISTS t_user;

CREATE TABLE t_user(
	id INT PRIMARY KEY auto_increment,
	username VARCHAR(20) NOT NULL UNIQUE,
	password VARCHAR(32) NOT NULL,
	email VARCHAR(200)
);

ALTER TABLE t_user CHARACTER SET utf8;

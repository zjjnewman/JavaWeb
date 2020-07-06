
DROP TABLE IF EXISTS t_book;

CREATE TABLE t_book(
	`id` INT PRIMARY KEY auto_increment,
	`name` VARCHAR(100),
	`price` DECIMAL(11, 2),
	`author` VARCHAR(100),
	`sales` INT,
	`stock` INT,
	`img_path` VARCHAR(200)
);
ALTER TABLE t_book CHARACTER SET utf8;




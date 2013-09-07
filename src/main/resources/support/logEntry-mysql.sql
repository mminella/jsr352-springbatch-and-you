DROP TABLE IF EXISTS logEntry;

CREATE TABLE logEntry  (
	id BIGINT NOT NULL AUTO_INCREMENT,
	ip_address CHAR(15),
	requested_url TEXT,
	country_code CHAR(2),
	view_date DATE,
	PRIMARY KEY (id)
);

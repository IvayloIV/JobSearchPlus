CREATE SCHEMA job_search_plus;

USE job_search_plus;

CREATE TABLE specialties (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	created_date DATE
);

CREATE TABLE JOB_STATUS (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(254) NOT NULL
);

CREATE TABLE users (
	faculty_number CHAR(10) PRIMARY KEY,
	egn CHAR(10),
  name VARCHAR(255) NOT NULL,
  middle_name VARCHAR(255),
  surname VARCHAR(255) NOT NULL,
  password VARCHAR(63) NOT NULL,
	specialty_id INT UNSIGNED NOT NULL,
	study_type VARCHAR(31) NOT NULL,
	phone VARCHAR(63),
	email VARCHAR(255),
	picture_name VARCHAR(511),
	grade DECIMAL(3, 2) UNSIGNED,
	job_status_id INT UNSIGNED NOT NULL,
	FOREIGN KEY (specialty_id) REFERENCES specialties(id),
	FOREIGN KEY (job_status_id) REFERENCES job_status(id)
);

CREATE TABLE ROLES (
	id VARCHAR(63) PRIMARY KEY,
  authority VARCHAR(31) NOT NULL UNIQUE
);

CREATE TABLE USERS_ROLES (
	user_id CHAR(10) NOT NULL,
  role_id VARCHAR(63) NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users(faculty_number),
  FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE JOB (
	record_id INT UNSIGNED PRIMARY KEY,
	title VARCHAR(511) NOT NULL,
  region VARCHAR(255) NOT NULL,
  salary VARCHAR(255),
  category VARCHAR(511),
  company_name VARCHAR(255),
  description TEXT,
  updated_date DATE NOT NULL,
  expire_date DATE
);

CREATE TABLE JOB_HISTORY (
	id INT UNSIGNED PRIMARY KEY,
	faculty_number CHAR(10) NOT NULL,
	job_record_id INT UNSIGNED NOT NULL,
	old_status_id INT UNSIGNED NOT NULL,
  new_status_id INT UNSIGNED NOT NULL,
  created_date DATE NOT NULL,
  FOREIGN KEY (faculty_number) REFERENCES users(faculty_number),
  FOREIGN KEY (job_record_id) REFERENCES job(record_id),
  FOREIGN KEY (old_status_id) REFERENCES job_status(id),
  FOREIGN KEY (new_status_id) REFERENCES job_status(id)
);

INSERT INTO specialties (name, created_date) VALUES ('AIT', NOW());
INSERT INTO specialties (name, created_date) VALUES ('KM', NOW());
INSERT INTO specialties (name, created_date) VALUES ('OC', NOW());
INSERT INTO specialties (name, created_date) VALUES ('KP', NOW());
commit;

INSERT INTO JOB_STATUS (name) VALUES('Unemployed');
INSERT INTO JOB_STATUS (name) VALUES('Applied');
INSERT INTO JOB_STATUS (name) VALUES('Rejected');
INSERT INTO JOB_STATUS (name) VALUES('Accepted');
INSERT INTO JOB_STATUS (name) VALUES('Left');
INSERT INTO JOB_STATUS (name) VALUES('Surrendered');
commit;
DROP TABLE IF EXISTS employee;

CREATE TABLE IF NOT EXISTS employee(id int PRIMARY KEY, name varchar(200), experience double);

DROP TABLE IF EXISTS project;

CREATE TABLE IF NOT EXISTS project(id int PRIMARY KEY, name varchar(200));

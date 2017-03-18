DROP TABLE IF EXISTS employee;

CREATE TABLE IF NOT EXISTS employee(id int PRIMARY KEY, name varchar(200), experience double);

DROP TABLE IF EXISTS project_table;

CREATE TABLE IF NOT EXISTS project_table(id int PRIMARY KEY, name varchar(200));

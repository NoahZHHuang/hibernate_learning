CREATE SEQUENCE employee_seq INCREMENT BY 1 START WITH 1;

CREATE TABLE employee(
  id INT NOT NULL DEFAULT nextval('employee_seq'),
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  salary INT NULL,
  PRIMARY KEY (id)
);
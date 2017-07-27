CREATE SEQUENCE role_seq INCREMENT BY 1 START WITH 1;

CREATE TABLE roles(
  id INT NOT NULL DEFAULT nextval('role_seq'),
  name TEXT NOT NULL,
  PRIMARY KEY (id)
);
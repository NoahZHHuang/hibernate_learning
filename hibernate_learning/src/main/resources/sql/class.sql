CREATE SEQUENCE class_seq INCREMENT BY 1 START WITH 1;

CREATE TABLE class(
  id INT NOT NULL DEFAULT nextval('class_seq'),
  name TEXT NOT NULL,
  CONSTRAINT pk_class_id PRIMARY KEY (id)
);
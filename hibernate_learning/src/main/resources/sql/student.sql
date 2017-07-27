CREATE SEQUENCE student_seq INCREMENT BY 1 START WITH 1;

CREATE TABLE student(
  id INT NOT NULL DEFAULT nextval('student_seq'),
  name TEXT NOT NULL,
  class_id INT NOT NULL,
  CONSTRAINT pk_student_id PRIMARY KEY (id),
  CONSTRAINT fk_class_id FOREIGN KEY (class_id) REFERENCES class(id)
);
CREATE SEQUENCE book_seq INCREMENT BY 1 START WITH 1;

CREATE TABLE book(
  id INT NOT NULL DEFAULT nextval('book_seq'),
  name TEXT NOT NULL,
  author TEXT NOT NULL,
  price REAL NOT NULL,
  sell_out CHAR(1) NOT NULL,
  PRIMARY KEY (id)
);
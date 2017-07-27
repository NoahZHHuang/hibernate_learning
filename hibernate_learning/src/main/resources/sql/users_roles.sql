CREATE TABLE users_roles(
  user_id INT,
  role_id INT,
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES roles(id)
);
INSERT INTO user
VALUES (default, 'jevinruv@gmail.com', 'Jevin', 'jevin123', 'ADMIN');
INSERT INTO user
VALUES (default, 'polo@gmail.com', 'Polo', 'polo123', 'User');


INSERT INTO category (id, code, name)
VALUES
  (NULL, 'vegetables', 'Vegetables'),
  (NULL, 'fruits', 'Fruits');

INSERT INTO role(name) VALUES ('ROLE_USER');
INSERT INTO role(name) VALUES ('ROLE_ADMIN');
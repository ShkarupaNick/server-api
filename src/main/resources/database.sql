--DROP TABLE "security".roles;
--DROP TABLE "security".users;


--DROP TABLE "security".roles;
--DROP TABLE "security".users;


/*CREATE TABLE users
(
  "id"       SERIAL       NOT NULL,
  username   VARCHAR(255) NOT NULL,
  "password" VARCHAR(255) NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE roles
(
  "id" SERIAL        NOT NULL,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE user_roles
(
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES security.users (id),
  FOREIGN KEY (role_id) REFERENCES security.roles (id),

  UNIQUE (user_id, role_id)
);*/

INSERT INTO users (username, password) VALUES ('syma', '123456');

INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES  (1,2);

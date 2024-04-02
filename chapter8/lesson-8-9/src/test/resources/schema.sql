CREATE TABLE IF NOT EXISTS member
(
    member_id INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email     VARCHAR(256) NOT NULL UNIQUE,
    password  VARCHAR(256) NOT NULL,
    name      VARCHAR(256) NOT NULL,
    age       INT          NOT NULL
);

CREATE TABLE IF NOT EXISTS role
(
    role_id   INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS member_has_role
(
    member_has_role_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    member_id          INT NOT NULL,
    role_id            INT NOT NULL
);

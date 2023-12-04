CREATE DATABASE mysecurity;

USE mysecurity;

CREATE TABLE member
(
    member_id INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email     VARCHAR(256) NOT NULL UNIQUE KEY,
    password  VARCHAR(256) NOT NULL,
    name      VARCHAR(256) NOT NULL,
    age       INT          NOT NULL
);

INSERT INTO member(email, password, name, age) VALUES ('test3@gmail.com', 333, 'Test 3', '20');
INSERT INTO member(email, password, name, age) VALUES ('test4@gmail.com', 444, 'Test 4', '30');
INSERT INTO member(email, password, name, age) VALUES ('test5@gmail.com', 555, 'Test 5', '22');
CREATE TABLE IF NOT EXISTS member
(
    member_id INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email     VARCHAR(256) NOT NULL UNIQUE,
    password  VARCHAR(256) NOT NULL,
    name      VARCHAR(256) NOT NULL,
    age       INT          NOT NULL
);

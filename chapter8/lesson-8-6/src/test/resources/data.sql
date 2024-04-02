-- 密碼的值為「帳號去掉 @gmail.com」，例如 admin@gmail.com 的密碼為 admin
INSERT INTO member(email, password, name, age) VALUES ('admin@gmail.com', '$2a$10$DuMH/aNBaYtHT0j/b2swG.0Z7eYJ8Sckw06PSdbY4Kqy4PzZxQJeu', 'Admin', '20');
INSERT INTO member(email, password, name, age) VALUES ('normal@gmail.com', '$2a$10$B/OtT9gtlgsh/w8tdO.ISeFHNNzcILIgRuh2vDwFRUSPGRC.WqWRq', 'Normal Member', '30');
INSERT INTO member(email, password, name, age) VALUES ('vip@gmail.com', '$2a$10$/cAcYaD3L4cvf09pC4YwkOB.KHPi.JzAXG1qoqtTVniaOeDog5Atm', 'VIP Member', '22');
INSERT INTO member(email, password, name, age) VALUES ('movie-manager@gmail.com', '$2a$10$4ygMq.sezGuSU7UxNPiVwefcWSVZq17z98MrTK1KhsO2AMQU1qbDa', 'Movie Manager', '25');

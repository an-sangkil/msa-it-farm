CREATE SCHEMA `naive-it-farm` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;


create user 'admin'@'%' identified by '1234';
grant all privileges on * to admin@'%';
flush privileges;
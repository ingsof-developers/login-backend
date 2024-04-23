DROP DATABASE IF EXISTS developers;
CREATE DATABASE IF NOT EXISTS developers;
USE developers;

CREATE TABLE `users` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT,
    `username` varchar(20) UNIQUE NOT NULL,
    `last_Name` varchar(20) NOT NULL,
    `maternal_Surname` varchar(20) NOT NULL,
    `password` varchar(255) NOT NULL,
    `email` varchar(50) UNIQUE NOT NULL
);

CREATE TABLE `roles` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(10) UNIQUE
);

CREATE TABLE `user_role` (
  `role_id` bigint,
  `user_id` bigint
);

ALTER TABLE `user_role` ADD CONSTRAINT `user_role_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `user_role` ADD CONSTRAINT `user_role_role_id_foreign` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO `roles` (`name`) VALUES ('ADMIN'), ('USER');
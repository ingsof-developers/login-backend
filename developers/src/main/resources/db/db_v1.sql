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

CREATE TABLE `departamentos` (
     `id` bigint PRIMARY KEY AUTO_INCREMENT,
     `name` varchar(50),
     `descripcion` varchar(50)
);

CREATE TABLE `students` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `correo` VARCHAR(50) NOT NULL,
    `telefono` VARCHAR(50) NOT NULL,
    `user_id` bigint UNIQUE
);


CREATE TABLE `tutores` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `correo` varchar(50) NOT NULL,
    `telefono` varchar(50) NOT NULL,
    `departamento_id` bigint NOT NULL,
    `user_id` bigint UNIQUE
);



CREATE TABLE `citas` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT,
    `fecha` date,
    `hora` time,
    `student_id` bigint,
    `tutor_id` bigint,
    `departamento_id` bigint
);

ALTER TABLE `user_role` ADD CONSTRAINT `user_role_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `user_role` ADD CONSTRAINT `user_role_role_id_foreign` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `departamentos` ADD CONSTRAINT `tutores_departamentos_id_foreign` FOREIGN KEY (`id`) REFERENCES `tutores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `users` ADD CONSTRAINT `tutores_users_id_foreign` FOREIGN KEY (`id`) REFERENCES `tutores` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `users` ADD CONSTRAINT `students_users_id_foreign` FOREIGN KEY (`id`) REFERENCES `students` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `students` ADD CONSTRAINT `citas_students_id_foreign` FOREIGN KEY (`id`) REFERENCES `citas` (`student_id`) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE `tutores` ADD CONSTRAINT `citas_tutores_id_foreign` FOREIGN KEY (`id`) REFERENCES `citas` (`tutor_id`) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE `departamentos` ADD CONSTRAINT `citas_departamentos_id_foreign` FOREIGN KEY (`id`) REFERENCES `citas` (`departamento_id`) ON DELETE SET NULL ON UPDATE CASCADE;

INSERT INTO `roles` (`name`) VALUES ('ADMIN'), ('USER');
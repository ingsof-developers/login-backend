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
                         `name` varchar(20) UNIQUE
);

CREATE TABLE `user_role` (
                             `role_id` bigint,
                             `user_id` bigint,
                             CONSTRAINT `user_role_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                             CONSTRAINT `user_role_role_id_foreign` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `students` (
                            `id` bigint PRIMARY KEY AUTO_INCREMENT,
                            `name` varchar(50) NOT NULL,
                            `correo` varchar(50) NOT NULL,
                            `telefono` varchar(50) NOT NULL,
                            `user_id` bigint UNIQUE,
                            CONSTRAINT `students_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `departamentos` (
                                 `id` bigint PRIMARY KEY AUTO_INCREMENT,
                                 `name` text,
                                 `description_info` TEXT,
                             `dates` TEXT,
                                `info` TEXT
);
CREATE TABLE `tutores` (
                           `id` bigint PRIMARY KEY AUTO_INCREMENT,
                           `name` varchar(50) NOT NULL,
                           `correo` varchar(50) NOT NULL,
                           `telefono` varchar(50) NOT NULL,
                           `departamento_id` bigint NOT NULL,
                           `user_id` bigint UNIQUE,
                           CONSTRAINT `tutores_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                           CONSTRAINT `tutores_departamento_id_foreign` FOREIGN KEY (`departamento_id`) REFERENCES `departamentos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE `citas` (
                         `id` bigint PRIMARY KEY AUTO_INCREMENT,
                         `fecha` date,
                         `hora` time,
                            `descripcion` longtext,
                         `student_id` bigint,
                         `tutor_id` bigint,
                         `departamento_id` bigint,
                         CONSTRAINT `citas_student_id_foreign` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
                         CONSTRAINT `citas_tutor_id_foreign` FOREIGN KEY (`tutor_id`) REFERENCES `tutores` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
                         CONSTRAINT `citas_departamento_id_foreign` FOREIGN KEY (`departamento_id`) REFERENCES `departamentos` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE `services`(
                            `id` bigint PRIMARY KEY AUTO_INCREMENT,
                            `name` text,
                            `descripcion` LONGTEXT,
                            `departamento_id` bigint,
                            CONSTRAINT `services_departamento_id_foreign` FOREIGN KEY (`departamento_id`) REFERENCES `departamentos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);


#INSERT INTO departamentos (name, descripcion)
# VALUES ('Psicologia', 'Departamento destinado a la ayuda psicologica'),
#        ('Actividad fisica', 'Departamento en el departamento de actividad fisica'),
#        ('nutriologia', 'Departamento dedicado a la ayuda en la alimentacion de las personas'),
#        ('Tutorias', 'Departamento dedicado a la mentoria academica');

INSERT INTO `departamentos` (`name`, `description_info`, `dates`, `info`) VALUES
    ('Psicología', 'Bienvenido al Departamento de Psicología de la Universidad de Guanajuato Campus División de Ingenierias Irapuato - Salamanca (DICIS), donde ofrecemos servicios de atención psicológica para estudiantes. Nuestro equipo de psicólogos altamente capacitados está aquí para brindarte apoyo y orientación en un ambiente confidencial y respetuoso.', 'Para programar una cita con uno de nuestros psicólogos, simplemente contáctanos a través del botón de generar cita. Nuestro equipo te guiará en el proceso de programación y te ofrecerá la atención que necesitas.', 'En el Departamento de Psicología de la Universidad de Guanajuato, estamos comprometidos a proporcionar servicios de atención psicológica de calidad para promover el bienestar y el desarrollo personal de nuestros estudiantes y la comunidad en general. ¡No dudes en ponerte en contacto con nosotros para obtener el apoyo que necesitas!'),
    ('Nutrición', 'Bienvenido al Departamento de Nutrición de la Universidad de Guanajuato Campus División de Ingenierías Irapuato - Salamanca (DICIS). Estamos comprometidos a promover una alimentación saludable y el bienestar nutricional de nuestros estudiantes y la comunidad en general. Nuestro equipo de nutricionistas certificados está aquí para brindarte apoyo y orientación en un ambiente confidencial y respetuoso.', 'Para programar una consulta con uno de nuestros nutricionistas, simplemente contáctanos a través del botón de generar consulta. Nuestro equipo te guiará en el proceso de programación y te ofrecerá la atención personalizada que necesitas para alcanzar tus objetivos nutricionales.', 'En el Departamento de Nutrición de la Universidad de Guanajuato, estamos comprometidos a proporcionar servicios de nutrición de calidad para promover la salud y el bienestar de nuestra comunidad. ¡No dudes en ponerte en contacto con nosotros para obtener el apoyo que necesitas para alcanzar tus metas nutricionales!'),
    ('Activación Física', 'Bienvenido al Departamento de Activación Física de la Universidad de Guanajuato Campus División de Ingenierías Irapuato - Salamanca (DICIS). Estamos comprometidos a promover un estilo de vida activo y saludable para nuestros estudiantes y la comunidad en general.', 'Para participar en nuestras actividades de activación física, simplemente ponte en contacto con nosotros a través del botón de registrar cita. ¡Nuestro equipo te ayudará a encontrar la actividad adecuada para ti y te guiará en tu viaje hacia un estilo de vida más activo y saludable!', 'En el Departamento de Activación Física de la Universidad de Guanajuato, estamos comprometidos a promover la salud y el bienestar a través del ejercicio y la actividad física. ¡Únete a nosotros y comienza tu viaje hacia una vida más activa y saludable hoy mismo!'),
    ('Orientación Educativa', 'Bienvenido al Departamento de Orientación Educativa de la Universidad de Guanajuato Campus División de Ingenierías Irapuato - Salamanca (DICIS), estamos comprometidos a brindar apoyo y orientación a nuestros estudiantes para ayudarlos a alcanzar su máximo potencial académico y personal.', 'Para aprovechar nuestros servicios de orientación educativa, simplemente contáctanos para programar una cita a través del boton registrar cita. Estamos aquí para ayudarte en cualquier etapa de tu viaje educativo, desde la orientación inicial hasta el desarrollo continuo a lo largo de tus estudios universitarios.', 'En el Departamento de Orientación Educativa de la Universidad de Guanajuato, estamos comprometidos a brindarte el apoyo que necesitas para tener éxito en tus estudios y en tu vida personal. ¡No dudes en comunicarte con nosotros para comenzar tu viaje hacia un futuro académico brillante!'),
    ('Tutoría', 'Bienvenido al Departamento de Tutoría de la Universidad de Guanajuato Campus División de Ingenierías Irapuato - Salamanca (DICIS), estamos aquí para apoyarte en tu camino académico y ayudarte a alcanzar tus metas educativas.', 'Para programar una sesión de tutoría, simplemente contáctanos a través del boton registrar cita. Ya sea que necesites ayuda con una asignatura específica o quieras mejorar tus habilidades de estudio en general. Nuestros tutores están aquí para ayudarte a tener éxito en tu educación universitaria.', 'En el Departamento de Tutoría de la Universidad de Guanajuato, estamos comprometidos a brindarte el apoyo académico que necesitas para alcanzar tus metas. ¡No dudes en comunicarte con nosotros y comenzar tu viaje hacia el éxito académico hoy mismo!')
;

INSERT INTO `services` (`id`, `name`, `descripcion`, `departamento_id`) VALUES
    (1, 'Asesoramiento y Orientación', 'Nuestros profesionales están disponibles para ayudarte a abordar una amplia gama de preocupaciones, incluyendo estrés, ansiedad, depresión, relaciones interpersonales, autoestima, entre otros.', 1),
    (2, 'Terapia Individual y de Grupo', 'Ofrecemos sesiones de terapia individual y de grupo, adaptadas a tus necesidades específicas, con el objetivo de ayudarte a mejorar tu bienestar emocional y psicológico.', 1),
    (3, 'Evaluación Psicológica', 'Realizamos evaluaciones psicológicas completas para ayudar a comprender mejor tus fortalezas, áreas de mejora y posibles problemas psicológicos.', 1),
    (4, 'Asesoramiento Nutricional Personalizado', 'Nuestros expertos en nutrición están disponibles para ayudarte a abordar una amplia gama de preocupaciones relacionadas con la alimentación y la nutrición, como la pérdida o ganancia de peso, la planificación de dietas equilibradas, las alergias alimentarias, y más.', 2),
    (5, 'Educación Alimentaria', 'Ofrecemos sesiones educativas para brindarte información sobre hábitos alimenticios saludables, lectura de etiquetas nutricionales, planificación de comidas y estrategias para mantener un estilo de vida nutricionalmente equilibrado.', 2),
    (6, 'Consulta Individual y en Grupo', 'Ya sea que prefieras sesiones individuales o grupales, nuestro equipo está aquí para adaptarse a tus necesidades. Ofrecemos consultas tanto en formato individual como en grupo para abordar tus preocupaciones de manera efectiva y brindarte el apoyo necesario.', 2),
    (7, 'Programas de Ejercicio Personalizados', 'Nuestros entrenadores especializados están disponibles para diseñar programas de ejercicio personalizados según tus objetivos de fitness y necesidades individuales.', 3),
    (8, 'Clases de Grupo', 'Ofrecemos una variedad de clases de grupo, que incluyen yoga, aeróbicos, baile y más, para ayudarte a mantenerte activo y motivado en un ambiente de apoyo y camaradería.', 3),
    (9, 'Asesoramiento en Estilo de Vida Saludable', 'Nuestros expertos en activación física brindan asesoramiento sobre hábitos de vida saludables, nutrición adecuada y estrategias para mantener una mente y un cuerpo saludables.', 3),
    (10, 'Asesoramiento Académico', 'Nuestros consejeros educativos están disponibles para ayudarte a planificar tu carrera académica, elegir tus cursos y programas de estudio, y desarrollar estrategias efectivas de aprendizaje.', 4),
    (11, 'Apoyo en la Toma de Decisiones', '¿Tienes dudas sobre tu futuro académico o profesional? Nuestro equipo de orientación está aquí para ayudarte a explorar tus intereses, habilidades y opciones para que puedas tomar decisiones informadas sobre tu educación y tu carrera.', 4),
    (12, 'Desarrollo Personal', 'Ofrecemos talleres y sesiones individuales para ayudarte a desarrollar habilidades de estudio, manejo del tiempo, resolución de problemas y toma de decisiones, que te serán útiles tanto en tu vida académica como en tu vida personal.', 4),
    (13, 'Apoyo Académico Personalizado', 'Nuestros tutores altamente calificados están disponibles para ayudarte en áreas específicas de tus estudios, ya sea matemáticas, ciencias, idiomas u otras asignaturas.', 5),
    (14, 'Asistencia en Tareas y Proyectos', '¿Necesitas ayuda con una tarea difícil o un proyecto importante? Nuestros tutores están aquí para proporcionarte la orientación y el apoyo que necesitas para tener éxito.', 5),
    (15, 'Desarrollo de Habilidades de Estudio', 'Además de ayudarte con contenido académico, nuestros tutores pueden brindarte consejos y técnicas para mejorar tus habilidades de estudio, organización y concentración.', 5)
;

INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER'),
       ('ROLE_TUTOR'),
       ('ROLE_STUDENT');

INSERT INTO users (username, last_Name, maternal_Surname, password, email)
VALUES ('Juan', 'Perez', 'Perez', '$2a$10$OcywHwvNHIqru6BG2IyiTee3yA73vy.KcyfAHnHq16gaao9SIF4A6', 'j.perezperez@ugto,mx');
INSERT INTO tutores (name, correo, telefono, departamento_id, user_id)
VALUES ('Juan Perez Perez', 'j.perezperez@ugto.mx', '4621234567', 1, 1);


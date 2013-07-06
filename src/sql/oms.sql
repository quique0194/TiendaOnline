SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `Portal_Descarga` ;
CREATE SCHEMA IF NOT EXISTS `Portal_Descarga` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
SHOW WARNINGS;
USE `Portal_Descarga` ;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Users` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Users` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `first_name` VARCHAR(64) NOT NULL ,
  `last_name` VARCHAR(64) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `state` TINYINT(1) NOT NULL ,
  `balance` FLOAT NOT NULL DEFAULT 0 ,
  `points` INT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Categories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Categories` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Categories` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `id_father` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Categories_1` (`id_father` ASC) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Promos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Promos` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Promos` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `start_date` DATE NOT NULL ,
  `end_date` DATE NOT NULL ,
  `percent` INT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Type_content`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Type_content` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Type_content` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `type_content` VARCHAR(64) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Type_file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Type_file` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Type_file` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `extension` VARCHAR(45) NOT NULL ,
  `mime` VARCHAR(45) NOT NULL ,
  `id_type_content` INT NOT NULL ,
  PRIMARY KEY (`id`, `id_type_content`) ,
  INDEX `fk_Tipo_archivo_Tipo_contenido1_idx` (`id_type_content` ASC) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Contents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Contents` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Contents` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `content` VARCHAR(512) NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `autor` VARCHAR(45) NULL ,
  `description` VARCHAR(45) NULL ,
  `price` FLOAT NOT NULL ,
  `size` INT NOT NULL ,
  `times_download` INT NOT NULL DEFAULT 0 ,
  `id_category` INT NOT NULL ,
  `id_promo` INT NOT NULL ,
  `id_type_file` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Contenido_Categoria1_idx` (`id_category` ASC) ,
  INDEX `fk_Contenido_Promocion1_idx` (`id_promo` ASC) ,
  INDEX `fk_Contenido_Tipo_archivo1_idx` (`id_type_file` ASC) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Payments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Payments` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Payments` (
  `id_user` INT NOT NULL ,
  `id_content` INT NOT NULL ,
  PRIMARY KEY (`id_user`, `id_content`) ,
  INDEX `fk_Pagos_Contenido1_idx` (`id_content` ASC) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Downloads`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Downloads` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Downloads` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `date` DATE NOT NULL ,
  `id_content` INT NOT NULL ,
  `id_user` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Descarga_Contenido1_idx` (`id_content` ASC) ,
  INDEX `fk_Descarga_Usuario1_idx` (`id_user` ASC) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Puntuations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Puntuations` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Puntuations` (
  `val` INT NOT NULL ,
  `id_content` INT NOT NULL ,
  `id_user` INT NOT NULL ,
  PRIMARY KEY (`id_content`, `id_user`) ,
  INDEX `fk_Puntuacion_Contenido1_idx` (`id_content` ASC) ,
  INDEX `fk_Puntuacion_Usuario1_idx` (`id_user` ASC) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Notifications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Notifications` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Notifications` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `detail` VARCHAR(256) NOT NULL ,
  `id_user` INT NOT NULL ,
  PRIMARY KEY (`id`, `id_user`) ,
  INDEX `fk_Notificacion_Usuario1_idx` (`id_user` ASC) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Vouchers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Vouchers` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Vouchers` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `points` INT NOT NULL ,
  `discount` TINYINT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Voucher_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Voucher_user` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Voucher_user` (
  `id_voucher` INT NOT NULL ,
  `id_user` INT NOT NULL ,
  `count` INT NULL ,
  PRIMARY KEY (`id_voucher`, `id_user`) ,
  INDEX `fk_Vale_Usuario_Usuario1_idx` (`id_user` ASC) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Administrator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Administrator` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Administrator` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `first_name` VARCHAR(64) NOT NULL ,
  `last_name` VARCHAR(64) NOT NULL ,
  `email` VARCHAR(128) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Tasks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Tasks` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Tasks` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `task` VARCHAR(256) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Logs_administrator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Logs_administrator` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Logs_administrator` (
  `date` DATETIME NOT NULL ,
  `detail` VARCHAR(256) NOT NULL ,
  `id_administrator` INT NOT NULL ,
  `id_task` INT NOT NULL ,
  PRIMARY KEY (`date`, `id_task`, `id_administrator`) ,
  INDEX `fk_Log_administrador_Administrador1_idx` (`id_administrator` ASC) ,
  INDEX `fk_Log_administrador_Tarea1_idx` (`id_task` ASC) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`SuperAdministrator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`SuperAdministrator` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`SuperAdministrator` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `first_name` VARCHAR(64) NOT NULL ,
  `last_name` VARCHAR(64) NOT NULL ,
  `email` VARCHAR(128) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- Insertions

insert into Users values(null,'kike', '123456', 'Jose Enrique', 'Carrillo Pino','quique0194@gmail.com', 1, 10, 10 );
insert into Users values(NULL,'mbustamante', '123456', 'Miguel Angel', 'Bustamante Cayo','miguel@gmail.com', 1, 100, 15 );

insert into Type_content values(null, 'image');
insert into Type_content values(null, 'audio');
insert into Type_content values(null, 'video');

insert into Type_file values(null, 'jpg', 'image/jpg', 1);
insert into Type_file values(null, 'png', 'image/png', 1);
insert into Type_file values(null, 'mp3', 'audio/mp3', 2);
insert into Type_file values(null, 'wav', 'audio/wav', 2);
insert into Type_file values(null, 'avi', 'video/avi', 3);
insert into Type_file values(null, 'mpg', 'video/mpg', 3);

insert into Contents values(null, '~/Contenido1', 'Contenido1', 'autor1', 'Contenido de prueba', 5, 5, 1, 4, 1, 1);
insert into Contents values(null, '~/Contenido2', 'Contenido2', 'autor2', 'Contenido de prueba', 10, 10, 1, 8, 2, 1);
insert into Contents values(null, '~/Contenido3', 'Contenido3', 'autor3', 'Contenido de prueba', 15, 15, 1, 12, 1, 2);
insert into Contents values(null, '~/Contenido4', 'Contenido4', 'autor4', 'Contenido de prueba', 25, 25, 1, 16, 2, 2);
insert into Contents values(null, '~/Contenido5', 'Contenido5', 'autor5', 'Contenido de prueba', 35, 35, 1, 20, 1, 3);
insert into Contents values(null, '~/Contenido6', 'Contenido6', 'autor6', 'Contenido de prueba', 40, 40, 1, 24, 2, 3);
insert into Contents values(null, '~/Contenido7', 'Contenido7', 'autor7', 'Contenido de prueba', 45, 45, 1, 28, 1, 4);
insert into Contents values(null, '~/Contenido8', 'Contenido8', 'autor8', 'Contenido de prueba', 50, 50, 1, 32, 2, 4);
insert into Contents values(null, '~/Contenido9', 'Contenido9', 'autor9', 'Contenido de prueba', 55, 55, 1, 36, 1, 5);
insert into Contents values(null, '~/Contenido10', 'Contenido10', 'autor10', 'Contenido de prueba', 60, 60, 1, 40, 2, 5);
insert into Contents values(null, '~/Contenido11', 'Contenido11', 'autor11', 'Contenido de prueba', 65, 65, 1, 44, 1, 6);
insert into Contents values(null, '~/Contenido12', 'Contenido12', 'autor12', 'Contenido de prueba', 70, 70, 1, 46, 2, 6);

insert into `Portal_Descarga`.`Administrator` (`username`, `password`, `first_name`, `last_name`, `email`) values('admi1', 'admi1', 'admi1', 'admi1','admi1@gmail.com');
insert into `Portal_Descarga`.`Administrator` (`username`, `password`, `first_name`, `last_name`, `email`) values('admi2', 'admi2', 'admi2', 'admi2','admi2@gmail.com');
insert into `Portal_Descarga`.`Administrator` (`username`, `password`, `first_name`, `last_name`, `email`) values('admi3', 'admi3', 'admi3', 'admi3','admi3@gmail.com');
insert into `Portal_Descarga`.`Administrator` (`username`, `password`, `first_name`, `last_name`, `email`) values('admi4', 'admi4', 'admi4', 'admi4','admi4@gmail.com');
insert into `Portal_Descarga`.`Administrator` (`username`, `password`, `first_name`, `last_name`, `email`) values('admi5', 'admi5', 'admi5', 'admi5','admi5@gmail.com');

insert into `Portal_Descarga`.`Tasks` (`task`) VALUES ('task1');
insert into `Portal_Descarga`.`Tasks` (`task`) VALUES ('task2');
insert into `Portal_Descarga`.`Tasks` (`task`) VALUES ('task3');
insert into `Portal_Descarga`.`Tasks` (`task`) VALUES ('task4');
insert into `Portal_Descarga`.`Tasks` (`task`) VALUES ('task5');

INSERT INTO `Portal_Descarga`.`Logs_administrator` (`date`, `detail`, `id_administrator`, `id_task`) VALUES ('19970523091529', 'detail2', 3, 5);
INSERT INTO `Portal_Descarga`.`Logs_administrator` (`date`, `detail`, `id_administrator`, `id_task`) VALUES ('19970523091530', 'detail3', 2, 1);
INSERT INTO `Portal_Descarga`.`Logs_administrator` (`date`, `detail`, `id_administrator`, `id_task`) VALUES ('19970523091531', 'detail5', 1, 2);
INSERT INTO `Portal_Descarga`.`Logs_administrator` (`date`, `detail`, `id_administrator`, `id_task`) VALUES ('19970523091532', 'detail4', 5, 4);
<<<<<<< HEAD
INSERT INTO `Portal_Descarga`.`Logs_administrator` (`date`, `detail`, `id_administrator`, `id_task`) VALUES ('19970523091529', 'detail2', 3, 5);
Insert Into `Portal_Descarga`.`Promos`(`start_date`,`end_date`,`percent`) VALUES ('12/12/12', '20/12/05', 20);
Insert Into `Portal_Descarga`.`Promos`(`start_date`,`end_date`,`percent`) VALUES ('01/12/12', '12/01/13', 15);
Insert Into `Portal_Descarga`.`Promos`(`start_date`,`end_date`,`percent`) VALUES ('12/01/12', '14/06/11', 5);
=======
INSERT INTO `Portal_Descarga`.`SuperAdministrator` ( `username`, `password`, `first_name`, `last_name`, `email`) VALUES ('Great_Kaiosama', '123456', 'Kaio', 'Sama', 'kaio_sama@god');
>>>>>>> a1a4f3b974f3bfc6a5d1a0ca8ecc5ef3c52ac2ff

insert into Type_file values(null,'jpg','mime',1);

INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (1, 'Todo el Contenido', null);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (2, 'Imagen', 1);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (3, 'Música', 1);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (4, 'Video', 1);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (5, 'Fotos', 2);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (6, 'Fondos de Pantalla', 2);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (7, 'Rock', 3);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (8, 'Romanticas', 3);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (9, 'Instrumentales', 3);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (10, 'Chinas', 3);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (11, 'Cristianas', 3);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (12, 'Pop', 3);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (13 , 'Punk', 3);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (14, 'Reggaetón', 3);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (15, 'Salsa', 3);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (16, 'Tango', 3);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (17, 'Trova', 3);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (18, 'Bachata', 3);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (19, 'Peliculas', 4);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (20, 'VideoTutoriales', 4);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (21, 'Series de Televisión', 4);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (22, 'Paisajes', 6);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (23, 'Animales', 6);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (24, 'Carros', 6);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (25, 'Rock en Español', 7);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (26, 'Rock de los 50', 7);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (27, 'Rock de los 60', 7);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (28, 'Rock de los 70', 7);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (29, 'Rock de los 80', 7);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (30, 'Rock Alternativo', 7);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (31, 'Rock Psicodélico', 7);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (32, 'Baladas', 8);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (33, 'Boleros', 8);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (34, 'Música Clásica', 9);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (35, 'Música Barroca', 9);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (36, 'Música Contemporanea', 9);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (37, 'Japonesas', 10);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (38, 'J-Pop', 37);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (39, 'J-Rock', 37);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (40, 'Koreanas', 10);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (41, 'K-Pop', 40);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (42, 'K-Rock', 40);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (43, 'Acción', 19);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (44, 'Comedia', 19);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (45, 'Terror', 19);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (46, 'Drama', 19);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (47, 'Infantil', 19);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (48, 'Anime', 21);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (49, 'Comedia', 21);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (50, 'Policiales', 21);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (51, 'Dibujos', 21);
INSERT INTO `Portal_Descarga`.`Categories` (`id`, `name`, `id_father`) VALUES (52, 'TeleNovelas', 21);


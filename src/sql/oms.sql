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
  `id_father` INT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_Categories` ON `Portal_Descarga`.`Categories` (`id_father` ASC) ;

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
  PRIMARY KEY (`id`, `id_type_content`) )
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_Tipo_archivo_Tipo_contenido1_idx` ON `Portal_Descarga`.`Type_file` (`id_type_content` ASC) ;

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
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_Contenido_Categoria1_idx` ON `Portal_Descarga`.`Contents` (`id_category` ASC) ;

SHOW WARNINGS;
CREATE INDEX `fk_Contenido_Promocion1_idx` ON `Portal_Descarga`.`Contents` (`id_promo` ASC) ;

SHOW WARNINGS;
CREATE INDEX `fk_Contenido_Tipo_archivo1_idx` ON `Portal_Descarga`.`Contents` (`id_type_file` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Portal_Descarga`.`Payments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Portal_Descarga`.`Payments` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `Portal_Descarga`.`Payments` (
  `id_user` INT NOT NULL ,
  `id_content` INT NOT NULL ,
  PRIMARY KEY (`id_user`, `id_content`) )
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_Pagos_Contenido1_idx` ON `Portal_Descarga`.`Payments` (`id_content` ASC) ;

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
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_Descarga_Contenido1_idx` ON `Portal_Descarga`.`Downloads` (`id_content` ASC) ;

SHOW WARNINGS;
CREATE INDEX `fk_Descarga_Usuario1_idx` ON `Portal_Descarga`.`Downloads` (`id_user` ASC) ;

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
  PRIMARY KEY (`id_content`, `id_user`) )
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_Puntuacion_Contenido1_idx` ON `Portal_Descarga`.`Puntuations` (`id_content` ASC) ;

SHOW WARNINGS;
CREATE INDEX `fk_Puntuacion_Usuario1_idx` ON `Portal_Descarga`.`Puntuations` (`id_user` ASC) ;

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
  PRIMARY KEY (`id`, `id_user`) )
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_Notificacion_Usuario1_idx` ON `Portal_Descarga`.`Notifications` (`id_user` ASC) ;

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
  PRIMARY KEY (`id_voucher`, `id_user`) )
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_Vale_Usuario_Usuario1_idx` ON `Portal_Descarga`.`Voucher_user` (`id_user` ASC) ;

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
  `date` DATE NOT NULL ,
  `detail` VARCHAR(256) NOT NULL ,
  `id_administrator` INT NOT NULL ,
  `id_task` INT NOT NULL ,
  PRIMARY KEY (`date`) )
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_Log_administrador_Administrador1_idx` ON `Portal_Descarga`.`Logs_administrator` (`id_administrator` ASC) ;

SHOW WARNINGS;
CREATE INDEX `fk_Log_administrador_Tarea1_idx` ON `Portal_Descarga`.`Logs_administrator` (`id_task` ASC) ;

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

insert into Users values(null,'kike', 'kike', 'Jose Enrique', 'Carrillo Pino','quique0194@gmail.com', 1, 10, 10 );
insert into Users values(NULL,'mbustamante', '123456', 'Miguel Angel', 'Bustamante Cayo','miguel@gmail.com', 1, 100, 15 );
insert into Contents values(1, 'rutaContenido1', 'contenido1', 'kike', 'contenido de prueba', 5, 18, 1, 1,1,1);
insert into Contents values(2, 'rutaContenido2', 'contenido2', 'kike', 'contenido de prueba', 10, 35, 1, 1,1,1);
insert into Administrator values(1,'admi1', 'admi1', 'admi1', 'admi1','admi1@gmail.com');
insert into Administrator values(2,'admi2', 'admi2', 'admi2', 'admi2','admi2@gmail.com');
insert into Administrator values(3,'admi3', 'admi3', 'admi3', 'admi3','admi3@gmail.com');
insert into Administrator values(4,'admi4', 'admi4', 'admi4', 'admi4','admi4@gmail.com');
insert into Administrator values(5,'admi5', 'admi5', 'admi5', 'admi5','admi5@gmail.com');
insert into Tasks  VALUES (null, 'task1');
insert into Tasks  VALUES (null, 'task2');
insert into Tasks  VALUES (null, 'task3');
insert into Tasks  VALUES (null, 'task4');
insert into Tasks  VALUES (null, 'task5');
insert into Type_file values(null,'jpg','mime',1);

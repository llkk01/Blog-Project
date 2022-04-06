-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db20
-- -----------------------------------------------------
DROP DATABASE IF EXISTS db20;
-- -----------------------------------------------------
-- Schema db20
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db20` DEFAULT CHARACTER SET utf8 ;
USE `db20` ;

-- -----------------------------------------------------
-- Table `db20`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db20`.`member` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `post` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `detailAddress` VARCHAR(100) NULL,
  `email` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NULL,
  PRIMARY KEY (`no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db20`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db20`.`board` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `contents` VARCHAR(200) NULL,
  `writer` VARCHAR(45) NULL,
  `rdate` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  `views` INT NULL DEFAULT 0,
  `writers` INT NOT NULL,
  PRIMARY KEY (`no`),
  INDEX `fk_board_member_idx` (`writers` ASC) VISIBLE,
  CONSTRAINT `fk_board_member`
    FOREIGN KEY (`writers`)
    REFERENCES `db20`.`member` (`no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db20`.`visitor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db20`.`visitor` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `contents` VARCHAR(500) NULL,
  `nickname` VARCHAR(45) NULL,
  `member_no` INT NOT NULL,
  `rdate` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  `writer` INT NULL,
  PRIMARY KEY (`no`),
  INDEX `fk_visitor_member1_idx` (`writer` ASC) VISIBLE,
  CONSTRAINT `fk_visitor_member1`
    FOREIGN KEY (`writer`)
    REFERENCES `db20`.`member` (`no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db20`.`home`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db20`.`home` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `profile` BLOB NULL,
  PRIMARY KEY (`no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db20`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db20`.`comment` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `contents` VARCHAR(100) NULL,
  `writer` VARCHAR(45) NULL,
  `rdate` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (`no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db20`.`report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db20`.`report` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `contents` VARCHAR(100) NULL,
  `reportcol` VARCHAR(45) NULL,
  PRIMARY KEY (`no`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

--admin계정
insert into member(name,id,password,post,address,detailAddress,email,state) values('admin','admin','1234','12345','서울','마포','admin@gmail.com,','1');

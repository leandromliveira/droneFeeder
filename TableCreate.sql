-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dronefeeder
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dronefeeder
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dronefeeder` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `dronefeeder` ;

-- -----------------------------------------------------
-- Table `dronefeeder`.`drone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dronefeeder`.`drone` (
  `id` INT NOT NULL,
  `available` BIT(1) NULL DEFAULT NULL,
  `latitude` VARCHAR(255) NULL DEFAULT NULL,
  `longitude` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dronefeeder`.`video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dronefeeder`.`video` (
  `id` INT NOT NULL,
  `video` LONGBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dronefeeder`.`delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dronefeeder`.`delivery` (
  `id` INT NOT NULL,
  `delivered_date` DATETIME NULL DEFAULT NULL,
  `delivery_status` VARCHAR(255) NULL DEFAULT NULL,
  `latitude` VARCHAR(255) NULL DEFAULT NULL,
  `longitude` VARCHAR(255) NULL DEFAULT NULL,
  `posted_date` DATETIME NULL DEFAULT NULL,
  `drone_id` INT NULL DEFAULT NULL,
  `video_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK4y03p3qe1k40wjerkrecqscl4` (`drone_id` ASC) VISIBLE,
  INDEX `FKpv3td2gml8vyvvyom0dp6x7gw` (`video_id` ASC) VISIBLE,
  CONSTRAINT `FK4y03p3qe1k40wjerkrecqscl4`
    FOREIGN KEY (`drone_id`)
    REFERENCES `dronefeeder`.`drone` (`id`),
  CONSTRAINT `FKpv3td2gml8vyvvyom0dp6x7gw`
    FOREIGN KEY (`video_id`)
    REFERENCES `dronefeeder`.`video` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dronefeeder`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dronefeeder`.`hibernate_sequence` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

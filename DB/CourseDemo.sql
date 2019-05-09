DataBase

CREATE TABLE `demodb`.`courses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));


CREATE TABLE `demodb`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
 `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `deleted` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));


CREATE TABLE `demodb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` TIMESTAMP NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `demodb`.`users_courses` (
  `id` INT NOT NULL  AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `course_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  `start_date` TIMESTAMP NULL,
  `end_date` TIMESTAMP NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_USERS_COURSES_USER_ID_idx` (`user_id` ASC),
  INDEX `FK_USERS_COURSES_COURSE_ID_idx` (`course_id` ASC),
  INDEX `FK_USERS_COURSES_ROLE_ID_idx` (`role_id` ASC),
  CONSTRAINT `FK_USERS_COURSES_USER_ID`
    FOREIGN KEY (`user_id`)
    REFERENCES `demodb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USERS_COURSES_COURSE_ID`
    FOREIGN KEY (`course_id`)
    REFERENCES `demodb`.`courses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USERS_COURSES_ROLE_ID`
    FOREIGN KEY (`role_id`)
    REFERENCES `demodb`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `demodb`.`users_courses_scores` (
      `id` INT NOT NULL AUTO_INCREMENT,
      `user_id` INT NOT NULL,
      `course_id` INT NOT NULL,
      `max_score` INT NULL,
      `score` INT NULL,
      `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      `deleted` TIMESTAMP NULL,
      PRIMARY KEY (`id`),
      INDEX `FX_USER_COURSE_SCORE_USER_ID_idx` (`user_id` ASC),
      INDEX `FX_USER_COURSE_SCORE_COURSE_ID_idx` (`course_id` ASC),
      CONSTRAINT `FX_USER_COURSE_SCORE_USER_ID`
        FOREIGN KEY (`user_id`)
        REFERENCES `demodb`.`users` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
      CONSTRAINT `FX_USER_COURSE_SCORE_COURSE_ID`
        FOREIGN KEY (`course_id`)
        REFERENCES `demodb`.`courses` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);

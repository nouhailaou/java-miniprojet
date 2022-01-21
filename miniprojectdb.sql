CREATE DATABASE IF NOT EXISTS miniprojet;

USE miniprojet;

CREATE TABLE IF NOT EXISTS salle
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    code VARCHAR(255)          NULL,
    type VARCHAR(255)          NULL,
    CONSTRAINT pk_salle PRIMARY KEY (id)
    );

DROP TABLE IF EXISTS Machine;

CREATE TABLE IF NOT EXISTS Machine
(
    id          BIGINT       NOT NULL,
    salle_id     BIGINT       NULL,
    `reference` VARCHAR(255) NULL,
    type        VARCHAR(255) NULL,
    dateAchat   datetime     NULL,
    CONSTRAINT pk_machine PRIMARY KEY (id)
    );
ALTER TABLE Machine
    ADD CONSTRAINT FK_MACHINE_ON_SALLE FOREIGN KEY (salle_id) REFERENCES salle (id);

CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT       NOT NULL,
    username VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
    );
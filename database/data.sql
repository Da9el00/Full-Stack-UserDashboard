CREATE DATABASE UserManagement;
USE UserManagement;

CREATE TABLE users(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO users (name, email, status)
VALUES("Hans", "hans@mail.com", "active");

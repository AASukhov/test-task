CREATE TABLE clients (
    client_id SERIAL,
    login VARCHAR(30),
    PRIMARY KEY (login)
);

CREATE TABLE phones (
    phone       BIGINT,
    client_id   INTEGER,
    primary key (phone)
);

CREATE TABLE emails (
    email       VARCHAR(50),
    client_id   INTEGER,
    primary key (email)
);

INSERT INTO clients (login)
VALUES ('Alex'),
       ('John'),
       ('Mike');

INSERT INTO phones (phone, client_id)
VALUES (1111111111, 1),
       (2222222222, 1),
       (3333333333, 1),
       (4444444444, 2),
       (5555555555, 2),
       (6666666666, 2),
       (7777777777, 3),
       (8888888888, 3),
       (9999999999, 3);

INSERT INTO emails(email, client_id)
VALUES ('alex@mail.ru', 1),
       ('alex2@mail.ru', 1),
       ('john3@mail.ru', 2);



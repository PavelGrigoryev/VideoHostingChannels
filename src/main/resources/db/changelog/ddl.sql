--liquibase formatted sql

--changeset Grigoryev_Pavel:1
CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    nickname VARCHAR(50)  NOT NULL UNIQUE,
    name     VARCHAR(50)  NOT NULL,
    email    VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS channels
(
    id                BIGSERIAL PRIMARY KEY,
    name              VARCHAR(40)                  NOT NULL UNIQUE,
    short_description VARCHAR(500)                 NOT NULL,
    author_id         BIGINT REFERENCES users (id) NOT NULL,
    created_at        TIMESTAMP                    NOT NULL,
    main_language     VARCHAR(20)                  NOT NULL,
    avatar            VARCHAR(100)                 NOT NULL,
    category          VARCHAR(20)                  NOT NULL
);

CREATE TABLE IF NOT EXISTS subscriptions
(
    user_id    BIGINT REFERENCES users (id),
    channel_id BIGINT REFERENCES channels (id),
    PRIMARY KEY (user_id, channel_id)
);

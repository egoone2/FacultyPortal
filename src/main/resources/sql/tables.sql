CREATE TABLE Groups
(
    id   bigserial,
    name varchar(100) NOT NULL,
    primary key (id)
);


CREATE TABLE Users
(
    id       bigserial,
    name     varchar(100) NOT NULL,
    surname  varchar(100),
    email    varchar(100) not null unique,
    password varchar      NOT NULL,
    role     varchar,
    group_id bigint,
    primary key (id),

    CONSTRAINT fk_group
        FOREIGN KEY (group_id)
            REFERENCES Groups (id)
);
--  TODO: переделать таблицы messages, files на отношение OneToMany
CREATE TABLE Messages
(
    id          bigserial,
    content     varchar,
    author_id   int,
    author_name varchar(100) not null default '',
    creation_time timestamp,
    primary key (id),

    CONSTRAINT fk_user
        FOREIGN KEY (author_id)
            REFERENCES Users (id),


);

CREATE TABLE Files
(
    id      bigserial,
    name    varchar NOT NULL UNIQUE,
    size_kb int,
    message_id bigint
    primary key (id),

    CONSTRAINT fk_message
        FOREIGN KEY (message_id)
            REFERENCES Messages (id)
);
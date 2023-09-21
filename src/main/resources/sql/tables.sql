CREATE TABLE Users
(
    id       int DEFAULT nextval('serial'),
    name     varchar(100) NOT NULL,
    surname  varchar(100),
    email    varchar(100) not null unique,
    password varchar      NOT NULL,
    role     varchar,
    primary key (id)
);

CREATE TABLE Messages
(
    id          int                   DEFAULT nextval('serial'),
    content     varchar,
    filename    varchar      not null default '',
    author_id   int,
    author_name varchar(100) not null default '',
    primary key (id),

    CONSTRAINT fk_user
        FOREIGN KEY (author_id)
            REFERENCES Users (id)
);
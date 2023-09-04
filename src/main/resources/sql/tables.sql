CREATE TABLE Users
(
    id       int DEFAULT nextval('serial'),
    username varchar(100) NOT NULL,
    password varchar      NOT NULL,
    role     varchar,
    primary key (id)
);

CREATE TABLE Messages
(
    id        int DEFAULT nextval('serial'),
    content   varchar,
    author_id int,
    primary key (id),
    CONSTRAINT fk_user
        FOREIGN KEY (author_id)
            REFERENCES Users (id)
);
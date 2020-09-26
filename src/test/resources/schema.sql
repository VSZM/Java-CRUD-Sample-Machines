DROP TABLE IF EXISTS machine;

CREATE TABLE machine(
    id CHAR(36) primary key,
    name char(250) not null,
    is_deleted boolean not null default false,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null
);

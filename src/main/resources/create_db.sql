CREATE DATABASE harisdb WITH ENCODING 'UTF8';

CREATE USER harisuser WITH ENCRYPTED PASSWORD '0FC&ZG$1Z3h3ZUBe2DIL@Yw9r';

CREATE TABLE machine(
    id CHAR(36) primary key,
    name char(250) not null,
    isDeleted boolean not null default false,
    createdAt timestamp with time zone not null,
    updatedAt timestamp with time zone not null
);

GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.machine TO harisuser;

CREATE TABLE machine(
    id CHAR(36) primary key,
    name char(250) not null,
    isDeleted boolean not null default false,
    createdAt timestamp with time zone not null,
    updatedAt timestamp with time zone not null
);

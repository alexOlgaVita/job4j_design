create table authors (
id serial primary key,
name varchar(255)
);

create table books (
id serial primary key,
name varchar(255),
author_id int references authors(id)
);

create table bookstores (
id serial primary key,
cost int,
book_id int references books(id)
);
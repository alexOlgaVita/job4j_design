-- 1. Создать таблицы (и заполнить их начальными данными)
create table departments (
id serial primary key,
name varchar(255)
);

create table employees (
id serial primary key,
name varchar(255),
department_id int references departments(id)
);
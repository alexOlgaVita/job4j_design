create table cars (
id serial primary key,
model varchar(255),
brand varchar(100),
volume numeric(2, 1),
color varchar(150),
export bool,
description text
);

insert into cars(model, brand, volume, color, export, description) values ('Corolla', 'Toyota', 2.5, 'grey', true, 'Комфортный автомобиль');

select * from cars;

update cars set export = false;

select * from cars;

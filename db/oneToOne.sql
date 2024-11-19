create table driver_lecenses (
id serial primary key,
number int
);

create table persons (
id serial primary key,
name varchar(255),
driver_lecense_id int references driver_lecenses(id) unique
);
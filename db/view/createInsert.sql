create table transport
(
    id   serial primary key,
    name varchar(100)
);
insert into transport (name) values ('Самолет');
insert into transport (name) values ('Трамвай');
insert into transport (name) values ('Автобус');

create table cities
(
    id   serial primary key,
    name varchar(250)
);
insert into cities (name) values ('Мухоморовка');
insert into cities (name) values ('Грязнушкино');
insert into cities (name) values ('Веселево');
insert into cities (name) values ('Хорошеево');
insert into cities (name) values ('Перепёловка');
insert into cities (name) values ('Перепёловка');

create table routs
(
    id   serial primary key,
	city_id1 integer references cities (id),
	city_id2 integer references cities (id),
    transport_id integer references transport (id)
);
insert into routs (city_id1, city_id2, transport_id) values (1, 2, 1);
insert into routs (city_id1, city_id2, transport_id) values (1, 3, 1);
insert into routs (city_id1, city_id2, transport_id) values (2, 3, 2);

create table persons
(
    id   serial primary key,
    name varchar(250)
);
insert into persons (name) values ('Олег Олегов');
insert into persons (name) values ('Роман Романов');
insert into persons (name) values ('Галина Галинова');
insert into persons (name) values ('Абдула Абдулаев');
insert into persons (name) values ('Кащей Кащеев');

create table tickets
(
    id serial primary key,
	routs_id integer references routs (id),
	person_id integer references persons (id),
    cost float
);
insert into tickets (routs_id, person_id, cost) values (1, 1, 15580.30);
insert into tickets (routs_id, person_id, cost) values (1, 2, 25580.30);
insert into tickets (routs_id, person_id, cost) values (1, 3, 35580.30);
insert into tickets (routs_id, person_id, cost) values (2, 1, 17580.30);
insert into tickets (routs_id, person_id, cost) values (3, 2, 5580.30);
insert into tickets (routs_id, person_id, cost) values (2, 4, 27580.30);
insert into tickets (routs_id, person_id, cost) values (2, 5, 37580.30);
insert into tickets (routs_id, person_id, cost) values (2, 5, 17580.30);
insert into tickets (routs_id, person_id, cost) values (2, 5, 18580.30
insert into tickets (routs_id, person_id, cost) values (2, 2, 47580.30);
insert into tickets (routs_id, person_id, cost) values (1, 2, 47580.30);
insert into tickets (routs_id, person_id, cost) values (3, 5, 5680.30);
insert into tickets (routs_id, person_id, cost) values (3, 5, 5780.30);
insert into tickets (routs_id, person_id, cost) values (3, 5, 5880.30);


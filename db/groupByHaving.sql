create table devices
(
    id serial primary key,
    name varchar(255),
    price float
);

create table people
(
    id serial primary key,
    name varchar(255)
);

create table devices_people
(
    id serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values ('Стиральная машинка', 5000.4), ('Супер утюжок', 5400.1), ('Автомобиль', 4000.78);
insert into people(name) values ('Маша'), ('Даша'), ('Клаша');
insert into devices_people(device_id, people_id) values (1, 1), (2, 1), (3, 1);
insert into devices_people(device_id, people_id) values (1, 2), (2, 2);
insert into devices_people(device_id, people_id) values (3, 3), (1, 3);

select avg(price) 
from devices;

select p.name, avg(ndp.price)
from
(
select d.price price, dp.people_id people_id
from devices_people as dp 
join devices d 
on dp.device_id = d.id
) ndp
join people p 
on ndp.people_id = p.id
group by p.name
order by avg(ndp.price);

select p.name, avg(ndp.price)
from
(
select d.price price, dp.people_id people_id
from devices_people as dp 
join devices d 
on dp.device_id = d.id
) ndp
join people p 
on ndp.people_id = p.id
group by p.name
having avg(ndp.price) > 5000
order by avg(ndp.price);


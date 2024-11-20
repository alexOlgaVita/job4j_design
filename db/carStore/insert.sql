-- 1. Написать SQL скрипты (по созданию таблиц и) их заполнением:
insert into car_bodies(name) 
values ('SEDAN'), ('HATCHBACK'), ('MINIVAN'), ('JEEP'), ('COUPE'), ('SPORTS CAR'), 
('STATION WAGON'), ('CONVERTIBLE'), ('SUV'), ('PICKUP TRUCK'), ('ELECTRIC CAR'), 
('CROSSOVER'), ('SPYDER'), ('HOT HATCH'), ('LIMOUSINE'), ('UTE'), ('PONY CAR'),
('SPORTS SEDAN'), ('MILITARY VEHICLE'), ('DRAGSTER'), ('SHOOTING BRAKE');

insert into car_engines(name) 
values ('2 Cylinder Straight Engines'), ('3 Cylinder Straight Engines'), 
('4 Cylinder Straight Engines'), ('5 Cylinder Straight Engines'),
('6 Cylinder Straight Engines'), ('8 Cylinder Straight Engines'), 
('2 Cylinder Flat Engines'), ('4 Cylinder Flat Engines'), ('6 Cylinder Flat Engines'),
('8 Cylinder Flat Engines'), ('8 Cylinder Flat Engines'), 
('6 Cylinder V Engines'), ('8 Cylinder V Engines'), ('8 Cylinder V Engines');

insert into car_transmissions(name) 
values ('Manual Transmission'), ('Automatic Transmissions'), 
('Continuously Variable Transmission')
, ('Dual-Clutch Transmission'), ('Semi-Automatic Transmissions');

insert into cars(name, body_id, engine_id, transmission_id) 
values ('Toyota Camry (XV70)', 1, 1, 2);

insert into cars(name, body_id, engine_id, transmission_id) 
values ('Toyota Corolla', 1, null, 2);

insert into cars(name, body_id, engine_id, transmission_id) 
values ('Ford Taurus', 1, 1, 2);

insert into cars(name, body_id, engine_id, transmission_id) 
values ('Ford Taurus', 1, 1, null);

insert into cars(name, body_id, engine_id, transmission_id) 
values ('Audi Q6 ', 12, 1, 2);

insert into cars(name, body_id, engine_id, transmission_id) 
values ('Audi Q5 ', null, 1, 2);

insert into cars(name, body_id, engine_id, transmission_id) 
values ('Audi Q3 ', null, null, null);
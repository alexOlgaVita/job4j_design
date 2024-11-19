create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('dog', 2000, '1000-01-01');
insert into fauna(name, avg_age, discovery_date) values ('cat', 1800, '1100-11-12');
insert into fauna(name, avg_age, discovery_date) values ('frog', 21000, '1950-05-02');
insert into fauna(name, avg_age, discovery_date) values ('cow', 5000, '0001-10-29');
insert into fauna(name, avg_age, discovery_date) values ('starfish', 12000, '1991-09-11');
insert into fauna(name, avg_age, discovery_date) values ('jellyfish', 13000, '1000-01-01');
insert into fauna(name, avg_age, discovery_date) values ('crayfish', 15000, null);

select * from fauna where name like '%fish%';
select * from fauna where avg_age < 21000 AND avg_age > 10000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
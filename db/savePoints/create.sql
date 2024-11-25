--drop table gifts;

create table gifts (
    id serial primary key,    
	name varchar(255),
	cost float,
    created_date timestamp
);

insert into gifts (name, cost, created_date) VALUES ('Открытка', 120.35, now());
insert into gifts (name, cost, created_date) VALUES ('Книга', 1899.99, now());
insert into gifts (name, cost, created_date) VALUES ('Кольцо', 2000.23, now());
insert into gifts (name, cost, created_date) VALUES ('Игра настольная', 1000.05, now());

select * from gifts;

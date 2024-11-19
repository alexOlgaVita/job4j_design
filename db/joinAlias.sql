create table authors (
id serial primary key,
name varchar(255)
);

create table books (
id serial primary key,
name varchar(255),
publishing_house varchar(255),
author_id int references authors(id)
);

insert into authors(name) values ('Пушкин');
insert into authors(name) values ('Чехов');
insert into authors(name) values ('Фет');

insert into books(name, publishing_house, author_id) values ('Руслан и Людмила', 'Детки-конфетки', 1);
insert into books(name, publishing_house, author_id) values ('Человек в футляре', 'Чудо печать', 2);
insert into books(name, publishing_house, author_id) values ('Я пришел к тебе с приветом', 'Просто мечта', 3);

select b.name, b.publishing_house, a.name 
from books as b join authors as a on b.author_id = a.id;

select b.name as Название, b.publishing_house as Издательство, a.name as Автор
from books as b join authors as a on b.author_id = a.id;

select b.name as "Название произведения", b.publishing_house as "Издательский дом", a.name Автор 
from books b join authors a on b.author_id = a.id;


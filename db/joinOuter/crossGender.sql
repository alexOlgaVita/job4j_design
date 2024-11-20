-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. 
-- Используя cross join составить все возможные разнополые пары. 
-- Исключите дублирование пар вида Вася-Маша и Маша-Вася.
create table teens (
id serial primary key,
name varchar(255),
gender varchar(255)
);

insert into teens (name, gender) values ('Вася', 'M');
insert into teens (name, gender) values ('Маша', 'Ж');
insert into teens (name, gender) values ('Леня', 'M');
insert into teens (name, gender) values ('Лена', 'Ж');
insert into teens (name, gender) values ('Дима', 'M');
insert into teens (name, gender) values ('Люся', 'Ж');
insert into teens (name, gender) values ('Стасик', 'M');
insert into teens (name, gender) values ('Наташа', 'Ж');
insert into teens (name, gender) values ('Олеся', 'Ж');
insert into teens (name, gender) values ('Вика', 'Ж');

select n1.name as aname, n1.gender as agender,
       n2.name as bname, n2.gender as b from (select * from teens order by name) as n1
         cross join (select * from teens order by name) as n2
		 where n1.name < n2.name; 
-- 1. (Создать таблицы) и заполнить их начальными данными
insert into departments(name) 
values ('Отдел1'), ('Отдел2'), ('Отдел3'), ('Отдел4'), ('Отдел5'), ('Отдел6'), ('Отдел7');

insert into employees(name, department_id) 
values ('Вася', 1), ('Маша', 2), ('Митя', 3), ('Лола', 4), ('Коля', 5);

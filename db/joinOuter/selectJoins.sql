-- 2. Выполнить запросы с left, right, full, cross соединениями

-- left, right
select * from departments d left join employees e on d.id=e.department_id;
select * from employees e right join departments d on d.id=e.department_id;

select * from employees e left join departments d on d.id=e.department_id;
select * from departments d right join employees e on d.id=e.department_id;

-- full
select * from departments d full join employees e on d.id = e.department_id;
-- аналог
select * from departments d left join employees e on d.id = e.department_id
union 
select * from departments d right join employees e on d.id = e.department_id;

-- cross
select * from departments d cross join employees e;

-- 3. Используя left join найти департаменты, у которых нет работников
select * from departments d left join employees e on d.id=e.department_id where e.name is null;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный). 
select * from departments d left join employees e on d.id=e.department_id where e.name is not null;
select * from departments d right join employees e on d.id=e.department_id;
-- или
select * from employees e left join departments d on d.id=e.department_id;
select * from employees e right join departments d on d.id=e.department_id where e.name is not null;

CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name)
VALUES (1, 'Без сучка'),
       (2, 'Не только колеса'),
       (3, 'Привет от старых штиблет'),
       (4, 'Фирма веников не вяжет'),
       (5, 'Просто 3 "О"'),
       (6, '3 пары калош');

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO person (id, name, company_id)
VALUES (1, 'Люда', 1),
       (2, 'Ваня', 5),
       (3, 'Вася', 2),
       (4, 'Люся', 5),
       (5, 'Емеля', 3),
       (6, 'Валя', 4),	   
       (7, 'Васислиса', 2),
       (8, 'Катя', 2),
       (9, 'Емельян', 5),
       (10, 'Лариса', 4);

-- 1. В одном запросе получить
-- - имена всех person, которые не состоят в компании с id = 5;
-- - название компании для каждого человека.
select p.name from person p where p.company_id!=5;

-- 2. Необходимо выбрать название компании с максимальным количеством человек + 
-- количество человек в этой компании.
-- Нужно учесть, что таких компаний может быть несколько.
select t.company_id, c.name, t.max_persons from  company c 
join
(
select p.company_id, count(*) as max_persons
from person p
group by p.company_id
having count(*)= (select max(total_person) 
from (select count(*) as total_person 
from person p 
group by p.company_id))
) t
on t.company_id=c.id 
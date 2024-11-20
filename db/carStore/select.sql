-- 2. Создать SQL запросы:

-- 1. Вывести список всех машин и все привязанные к ним детали. 
-- Нужно учесть, что каких-то деталей машина может и не содержать. 
-- В таком случае значение может быть null при выводе (например, название двигателя null);
-- Пример "шапки" при выводе:
-- id, car_name, body_name, engine_name, transmission_name
select c.id, c.name car_name, b.name body_name, e.name engine_name, t.name transmission_name
from cars c left join car_bodies b on c.body_id=b.id
left join car_engines e on c.engine_id=e.id
left join car_transmissions t on c.transmission_id=t.id;

-- 2. Вывести кузова, которые не используются НИ в одной машине. 
-- "Не используются" значит, что среди записей таблицы cars отсутствует внешние ключи, 
-- ссылающие на таблицу car_bodies. Например, Вы добавили в car_bodies "седан", "хэтчбек" и "пикап", 
-- а при добавлении в таблицу cars указали только внешние ключи на записи "седан" и "хэтчбек". 
-- Запрос, касающийся этого пункта, должен вывести "пикап", т.к. среди машин нет тех, что обладают таким кузовом;
select b.id, b.name from car_bodies b left join cars c on c.body_id=b.id where c.name is null
order by b.id;

-- 3. Вывести двигатели, которые не используются НИ в одной машине, аналогично п.2;
select e.id, e.name from car_engines e left join cars c on c.engine_id=e.id where c.name is null
order by e.id;

-- 4. Вывести коробки передач, которые не используются НИ в одной машине, аналогично п.2.
select t.id, t.name from car_transmissions t left join cars c on c.transmission_id=t.id  where c.name is null
order by t.id;



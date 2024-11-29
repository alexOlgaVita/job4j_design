-- 1.
-- Добавьте в таблицу несколько записей.
-- Выполните запрос, который вернет список клиентов, возраст которых является минимальным.

CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers (first_name, last_name, age, country)
VALUES ('Оля', 'Ильина', 18, 'Россия');
insert into customers (first_name, last_name, age, country)
VALUES ('Алёна', 'Жукова', 28, 'Испания');
insert into customers (first_name, last_name, age, country)
VALUES ('Олег', 'Прохоров', 35, 'США');
insert into customers (first_name, last_name, age, country)
VALUES ('Луиза', 'Шестакова', 50, 'Россия');
insert into customers (first_name, last_name, age, country)
VALUES ('Андрей', 'Санников', 42, 'Россия');
insert into customers (first_name, last_name, age, country)
VALUES ('Алексей', 'Бабушкин', 31, 'Россия');
insert into customers (first_name, last_name, age, country)
VALUES ('Иван', 'Иванов', 27, 'Россия');
insert into customers (first_name, last_name, age, country)
VALUES ('Ирина', 'Александрова', 41, 'Россия');


-- 2. К уже представленной таблице customers добавляем следующую таблицу:
-- Добавьте в таблицу несколько записей.
-- Необходимо выполнить запрос, который вернет список пользователей, которые еще не выполнили ни одного заказа. 
-- Используйте подзапрос, для реализации Вам понадобится NOT IN.
CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders (amount, customer_id)
VALUES (1, 1);
insert into orders (amount, customer_id)
VALUES (2, 1);
insert into orders (amount, customer_id)
VALUES (3, 1);
insert into orders (amount, customer_id)
VALUES (6, 2);
insert into orders (amount, customer_id)
VALUES (8, 3);

select * from customers c where c.id NOT in (select customer_id from orders);
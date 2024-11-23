create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace procedure delete_data_by_count(d_count integer)
language 'plpgsql'
as $$
    BEGIN
            delete from products where count >= d_count;
    END;
$$;

create
or replace function f_delete_data_by_id(d_id integer)
returns void
language 'plpgsql'
as
$$
    begin
           delete from products where id = d_id;
    end;
$$;

-- очищение таблиц и обнуение счетчика, для повторного наполнения "с нуля"
delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

-- вставка записей
insert into products (name, producer, count, price) values ('Шапка-ушанка', 'ООО Бибигуль', 3, 750);
insert into products (name, producer, count, price) values ('Скатерть-самобранка', 'ООО Родные просторы', 5, 7999);
insert into products (name, producer, count, price) values ('Кружка бездонная', 'ИП Самоделкин', 4, 100);
insert into products (name, producer, count, price) values ('Серьезная игра', 'ИП Несмеян Н.Н.', 4, 937);
insert into products (name, producer, count, price) values ('Клюшка для реальных пацанов', 'ООО Спорт и никаких гвоздей', 6, 12000);
insert into products (name, producer, count, price) values ('Мышиный хвостик', 'ИП Хомякова Х.Х.', 6, 199);

-- проверка наполнения
select * from products;

-- вызов функции
select f_delete_data_by_id(4);

-- вызов процедуры
call delete_data_by_count(4);

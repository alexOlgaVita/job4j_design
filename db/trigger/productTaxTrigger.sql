-- 1)  Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог на товар 
-- (нужно прибавить налог к цене товара). Действовать он должен не на каждый ряд, а на запрос (statement уровень)
create
or replace function taxState()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.25
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';	

create trigger taxState_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure taxState();

-- 2) Триггер должен срабатывать до вставки данных и насчитывать налог на товар (нужно прибавить налог к цене товара).
-- Здесь используем row уровень.
create
or replace function tax()
    returns trigger as
$$
    BEGIN
		NEW.price = NEW.price + NEW.price * 0.15;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';	

create trigger tax_trigger
    before insert
    on products
    for each row
    execute procedure tax();

--drop trigger tax_trigger on products;
--drop function tax;
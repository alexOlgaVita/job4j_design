-- Нужно написать триггер на row уровне, который сразу после вставки продукта в таблицу products, 
-- будет заносить имя, цену и текущую дату в таблицу history_of_price. 
create
or replace function historyFill()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
        VALUES (new.name, new.price, now());		
        return new;
    END;
$$
LANGUAGE 'plpgsql';	

create trigger historyFill_trigger
    after insert
    on products
    FOR EACH ROW
    execute procedure historyFill();

--drop trigger historyFill_trigger on products;
--drop function historyFill;

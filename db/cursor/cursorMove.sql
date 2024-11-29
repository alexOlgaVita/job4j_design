-- начало транзакции
BEGIN;
-- объявление курсора с возможностью прокрутки
DECLARE
cursor_products SCROLL cursor for
select * from products;

-- перемещение на последнюю запись
FETCH LAST from cursor_products;

-- перемещение с послелней (20й) записи на 15
MOVE BACKWARD 4 FROM cursor_products;
FETCH PRIOR FROM cursor_products;

-- перемещение с 15й записи на 7
MOVE BACKWARD 7 FROM cursor_products;
FETCH PRIOR FROM cursor_products;

-- перемещение с 7й записи на 2ю
MOVE BACKWARD 4 FROM cursor_products;
FETCH PRIOR FROM cursor_products;

-- перемещение на первую запись
FETCH FIRST from cursor_products;

-- закрытие курсора
CLOSE cursor_products;
-- завершеие транзакции
ROLLBACK;

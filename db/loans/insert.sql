insert into roles(name) values ('Директор');

insert into roles(name) values ('Учитель');

insert into roles(name) values ('Ученик');

insert into rules(name) values ('Заказ');

insert into rules(name) values ('Отправка');

insert into rules(name) values ('Отказ');

insert into roles_rules(role_id, rule_id) values (1, 1);
insert into roles_rules(role_id, rule_id) values (1, 2);
insert into roles_rules(role_id, rule_id) values (1, 3);
insert into roles_rules(role_id, rule_id) values (2, 1);
insert into roles_rules(role_id, rule_id) values (2, 2);
insert into roles_rules(role_id, rule_id) values (3, 3);

insert into users(name, role_id) values ('Федя', 1);
insert into users(name, role_id) values ('Вася', 2);
insert into users(name, role_id) values ('Лариса', 3);

insert into states(name) values ('Собирается');
insert into states(name) values ('Отправляется');
insert into states(name) values ('Доставка');
insert into states(name) values ('Доставлен');
insert into states(name) values ('Выдан');
insert into states(name) values ('Отправлен обратно');

insert into categories(name) values ('Срочная доставка');
insert into categories(name) values ('Несрочная доставка');

insert into items(user_id, category_id, state_id) values (1, 1, 1);
insert into items(user_id, category_id, state_id) values (1, 2, 1);
insert into items(user_id, category_id, state_id) values (1, 2, 2);
insert into items(user_id, category_id, state_id) values (2, 2, 2);

insert into comments(name, item_id) values ('Адрес уточнить при созвоне', 1);
insert into comments(name, item_id) values ('Позвонить не позднее, чем за пол дня', 2);

insert into attachs(name, item_id) values ('Картинка', 1);
insert into attachs(name, item_id) values ('Марка', 2);

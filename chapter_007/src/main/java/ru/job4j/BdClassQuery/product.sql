
--В системе заданы таблицы
--product(id, name, type_id, expired_date, price)
--type(id, name)
--Задание.

create database products;

create table type  (
                       id serial primary key,
                       name character varying (2000)
);

create table product (
		id serial primary key,
		name character varying (2000),
		type_id int references type(id),
		expired_date timestamp,
		price int
);


insert into type (name) values ('сыр');
insert into type (name) values ('молоко');
insert into type (name) values ('мясо');

insert into product (name, type_id, expired_date, price) values ('пошехонский', 1, '2014-04-04 20:00:00', 23);
insert into product (name, type_id, expired_date, price) values ('костромской', 1, '2014-04-04 20:00:00', 26);
insert into product (name, type_id, expired_date, price) values ('мороженое', 2, '2014-04-04 20:00:00', 15);
insert into product (name, type_id, expired_date, price) values ('кефир', 2, '2014-04-04 20:00:00', 65);
insert into product (name, type_id, expired_date, price) values ('свинина', 3, '2014-04-04 20:00:00', 65);
insert into product (name, type_id, expired_date, price) values ('говядина', 3, '2014-04-04 20:00:00', 65);
insert into product (name, type_id, expired_date, price) values ('поросятина', 3, '2019-09-26 20:00:00', 65);

--1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product where type_id = 1;

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product as p where p.name LIKE '%мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product as p where p.expired_date BETWEEN
                                     date_trunc('month', now())
                                     AND date_trunc('month', now()) + interval '2 month';

--4. Написать запрос, который выводит самый дорогой продукт.
select * from product where price in (select max(price) from product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select COUNT(type_id) FROM product WHERE type_id = 3;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product where type_id = 1 or type_id = 2;

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select count(*), t.name from type as t  inner join product as p on t.id = p.type_id
GROUP BY t.name having count(type_id) < 3;

--8. Вывести все продукты и их тип.
select p.name as product, t.name as type from product as p inner join type as t on t.id = p.type_id;

--9. запрос, который выведет количество продуктов каждого типа.
select count(*), t.name from type as t  inner join product as p on t.id = p.type_id
GROUP BY t.name;
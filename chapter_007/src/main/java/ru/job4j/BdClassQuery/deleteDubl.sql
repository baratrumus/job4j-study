--В системе есть таблица cities. с полями id, name.
--Система парсит объявления и записываeт города.
--В коде системы оказался баг. Он записывал дубликаты городов.
--Москва, Москва, СПб, Казань.
--Багу поправили на уровне приложения, но таблица все равно содержит дубликаты.
--Ваша задача написать sql скрипт, который оставит в таблице только уникальные города. Если было три раза написана Москва. то нужно оставить только одну Москву.

create table cities (
                         id serial primary key,
                         name character varying (2000)

);

insert into cities (name) values ('Moscow');
insert into cities (name) values ('Moscow');
insert into cities (name) values ('Moscow');
insert into cities (name) values ('Piter');
insert into cities (name) values ('Piter');

--1 вариант
CREATE TEMPORARY TABLE city_temp
as  (
    SELECT min(id) as id
    FROM cities
    GROUP BY name
    );

DELETE from cities as c
WHERE c.id not in (
   SELECT id FROM city_temp
);

--2 вариант
delete from cities where id not in (select min(id) from cities group by name);

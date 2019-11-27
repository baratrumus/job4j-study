
/*
Нужно написать SQL скрипты:
Создать структур данных в базе.
Таблицы.
   Кузов. Двигатель, Коробка передач.
Создать структуру Машина. Машина не может существовать без данных из п.1.
Заполнить таблицы через insert.

*/

create database cars;

create table body (
    id serial primary key,
    body_name character varying(2000)
);

create table engine (
    id serial primary key,
    engine_name character varying(2000)
);

create table gearbox (
     id serial primary key,
     box_name character varying(2000)
);

create table cars (
     id serial primary key,
     car_name character varying(2000),
     body_id int references body(id),
     engine_id int references engine(id),
     gearbox_id int references gearbox(id)
);

insert into body (body_name) values ('седан');
insert into body (body_name) values ('универсал');
insert into body (body_name) values ('пикап');

insert into engine (engine_name) values ('2 цилиндра');
insert into engine (engine_name) values ('4 цилиндра');
insert into engine (engine_name) values ('6 цилиндров');

insert into gearbox (box_name) values ('ручная');
insert into gearbox (box_name) values ('автоматическая');

insert into cars (car_name, body_id, engine_id, gearbox_id) VALUES ('митсубиси', '1', '2', '1');
insert into cars (car_name, body_id, engine_id, gearbox_id) VALUES ('субару', '2', '2', '2');
insert into cars (car_name, body_id, engine_id, gearbox_id) VALUES ('тойота', '1', '3', '1');

--Создать SQL запросы:
--1. Вывести список всех машин и все привязанные к ним детали.
select c.car_name, b.body_name, e.engine_name, g.box_name from cars as c
    left outer join body as b on c.body_id = b.id
    left outer join engine as e on c.engine_id = e.id
    left outer join gearbox as g on c.gearbox_id = g.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select b.body_name, e.engine_name, g.box_name from cars as c
     full join body as b on c.body_id = b.id
     full join engine as e on c.engine_id = e.id
     full join gearbox as g on c.gearbox_id = g.id
     where c.id is null;
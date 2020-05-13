
--Дана таблица координат банкоматов с тремя колонками id, longtitude, latitude.
-- Необходимо написать sql-запрос который находит ближайший идентификатор банкомата для заданных координат.
-- Можно считать что координаты записаны в x0, y0 и к ним можно обращаться.


-- Надо взять макс значение, которое меньше(слева) от Х.
-- взять мин значение из тех что справа от X
-- Вычесть из кажого сам Х, взять каждое по модулю.
-- Наименьшее это ближ Х

create table bankxy (
                        id serial primary key,
                        lon int,
                        lat int
);

insert into bankxy(lon, lat) values (1, 3);
insert into bankxy(lon, lat) values (2, 6);
insert into bankxy(lon, lat) values (3, 1);
insert into bankxy(lon, lat) values (6, 6);
insert into bankxy(lon, lat) values (5, 1);


--1 вариант
select id, max(longtitude), max(latitude) from table as t
where longtitude < x0 and latitude < y0
group by longtitude, latitude;


--2 вариант
--выполняем сортировку по возрастанию и берем 1 верхний, т.е. самый маленький из выборки
select id from table as t
where t.longtitude > x0
order by t.longtitude limit 1

    union select id from table as t
          where t.latitude > y0
          order by t.latitude limit 1;

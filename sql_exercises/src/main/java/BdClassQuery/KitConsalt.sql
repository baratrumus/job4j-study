--Дана таблица координат банкоматов с тремя колонками id, longtitude, latitude.
-- Необходимо написать sql-запрос который находит ближайший идентификатор банкомата для заданных координат.
-- Можно считать что координаты записаны в x0, y0 и к ним можно обращаться.

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


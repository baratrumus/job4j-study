--Выберите из таблицы workers записи с id равным 1, 2, 3, 5, 14.
select * from workers as w where w.id in (1,2,3,5,11);

--среднюю зарплату
SELECT AVG(salary) FROM workers;

--суммарную зарплату для людей в возрасте от 21 до 25.
SELECT SUM(salary) FROM workers WHERE age BETWEEN 21 AND 25;

--все записи, у которых дата больше текущей.
SELECT * FROM workers WHERE date > CURRENT_DATE();

--Вставьте в таблицу workers запись с полем date с текущим моментом времени в формате
-- 'год-месяц-день часы:минуты:секунды'.
INSERT INTO workers ('name', 'date') VALUES ('Вася', NOW());

--CURTIME()

--все записи за 2016 год.
SELECT * FROM workers WHERE YEAR(date) = 2016;

--WHERE MONTH(date) = 3
--WHERE DAY(date) = 3
--WHERE DAYOFWEEK(date)= 3

--запишите год, месяц и день в отдельные поля с помощью EXTRACT.
SELECT EXTRACT(YEAR FROM date) AS year,
       EXTRACT(MONTH FROM date) AS month,
       EXTRACT(DAY FROM date) AS day
FROM workers;

SELECT DATE_FORMAT(date, '%d.%m.%Y') FROM workers;

SELECT DATE_ADD(date, INTERVAL -1 DAY) FROM workers;
SELECT DATE_ADD(date, INTERVAL "1:2" DAY_HOUR) FROM workers;
SELECT DATE_ADD(date, INTERVAL "1:2" YEAR_MONTH) FROM workers;
--1 день, 2 часа, 3 минуты.
SELECT DATE_SUB(date, INTERVAL "1:2:3" DAY_SECOND) FROM workers;
--1 день, 2 часа, 3 минуты, 5 секунд
SELECT DATE_SUB(date, INTERVAL "1 2:3:5" DAY_SECOND) FROM workers;
--прибавьте к дате 1 день и отнимите 2 часа
SELECT DATE_ADD(date, INTERVAL 1:-2 DAY_HOUR) FROM workers;
--прибавьте к дате 1 день и отнимите 2 часа, 3 минуты
SELECT DATE_ADD(date, INTERVAL 1 -2 -3 DAY_MINUTE) FROM workers;

--создайте новое поле res, в котором будет лежать сумма зарплаты и возраста.
SELECT SUM(age) AND SUM(salary) AS res FROM workers;


--самые маленькие и большие зарплаты по группам возрастов (для каждого возраста свою минимальную зарплату)
SELECT MIN(salary), MAX(salary) FROM workers GROUP BY age;

--самый большой возраст по группам зарплат (для каждой зарплаты свой максимальный возраст)
SELECT MAX(age) FROM workers GROUP BY salary;

--все записи, зарплата в которых больше средней зарплаты
SELECT * FROM workers WHERE salary >(SELECT AVG(salary) FROM workers)

-- все записи, возраст в которых меньше среднего возраста, деленного на 2 и умноженного на 3
SELECT * from workers where age < (SELECT (avg(age)/2*3));

--	Даны две таблицы: таблица category с полями id и name и таблица page с полями id, name и category_id.
--	Достаньте одним запросом все страницы вместе с их категориями
SELECT * from page LEFT outer join category on  page.categoty_id = category.id;



CREATE DATABASE test1;
DROP DATABASE test2;
CREATE TABLE table1(
                       id INT(1),
                       login VARCHAR(255),
                       salary INT(6),
                       age INT(6),
                       date DATE NOT NULL
                           primary key (Id));
RENAME TABLE table2 TO table3;
DROP TABLE table3;
-- Добавьте в таблицу table1 поле status
ALTER TABLE table1 ALTER COLUMN status;
ALTER TABLE table1 DROP COLUMN age;
RENAME COLUMN login TO user_login;
--тип поля salary с int на varchar(255)
ALTER TABLE table1 CHANGE salary salary VARCHAR(255);
DELETE FROM table1;
--Очистите все таблицы базы данных test1
TRUNCATE test1;

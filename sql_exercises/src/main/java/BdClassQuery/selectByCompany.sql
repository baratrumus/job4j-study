
--есть две таблицы нужно

--2) Select the name of the company with the maximum number of persons + number of persons in this company

CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name) values (1, 'торговая компания');
insert into company (id, name) values (2, 'военная  компания');
insert into company (id, name) values (5, 'учебная компания');


insert into person (id, name, company_id) values (1, 'vasiliy', 1);
insert into person (id, name, company_id) values (2, 'petr', 1);
insert into person (id, name, company_id) values (3, 'masha', 2);
insert into person (id, name, company_id) values (4, 'dasha', 2);
insert into person (id, name, company_id) values (5, 'lesha', 5);
insert into person (id, name, company_id) values (6, 'tosha', 5);
insert into person (id, name, company_id) values (7, 'ivan', 5);

--1) Retrieve in a single query:
-- names of all persons that are NOT in the company with id = 5
-- company name for each person
select p.id, p.name, c.name from person as p left outer join company as c on c.id = p.company_id where c.id <> 5;

--2) Select the name of the company with the maximum number of persons + number of persons in this company
select count(p.company_id), c.name from person as p left outer join company as c on c.id = p.company_id
group by c.name ORDER BY count(p.id) DESC LIMIT 1;

select tbl.nm, max(tbl.cnt) from
    (select c.name as nm, count(c.id) as cnt from person as p left join company as c on c.id = p.company_id
     group by c.id) as tbl



--
-- select top 1 t1.*
-- from (select profit.source,count(*) as expr1
--       from profit, have_d
--       where profit.id = have_d.id
--       group by profit.source) as t1
-- order by expr1 desc
--
-- select t1.*
--
-- from (select profit.*,count(*) as expr1
--       from profit, have_d
--       where profit.id = have_d.id
--       group by profit.id
--       order by expr1 desc) as t1
-- limit 1





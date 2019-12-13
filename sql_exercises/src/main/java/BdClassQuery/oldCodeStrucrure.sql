--Товар (название, цена, количество), категория товара.
-- достать товары из категорий 'Овощи', 'Мясо', 'Морепродукты',
select * from goods Inner JOIN  category as c on goods.category_id = c.id
where c.name IN ('Овощи', 'Мясо', 'Морепродукты');

--достать все категории, в которых есть товары (без товаров, только названия категорий, без дублей)
SELECT c.name from category as c where c.id in (select g.id from goods as g);
select DISTINCT c.name from category as c INNER JOIN goods on goods.category_id = c.id;
-- все продукты с типом 'сыр'
select * from product pr
inner join type t on pr.type_id = t.id
                         and t.name = 'сыр';

-- все продукты со словом "мороженное" в имени
select * from product
where name like '%мороженное%';

-- все продукты, срок годности которых заканчивается в следующем месяце
select * from product
where date_part('month', expired_date) = date_part('month', current_date + interval '1 month')
and date_part('year', expired_date) >= date_part('year', current_date);

-- самый дорогой продукт
select * from product
order by price desc
limit 1;

-- количество продуктов, принадлежащих к типу
select t.name, count(pr.id) from product pr
inner join type t on pr.type_id = t.id
group by t.id;

-- все продукты с типом "СЫР" и "МОЛОКО"
select * from product
inner join type t on product.type_id = t.id
                         and t.name in ('сыр', 'молоко');

-- типы продуктов, которых осталось меньше 10 штук
select t.name from product pr
inner join type t on pr.type_id = t.id
group by t.id
having count(pr.id) < 10;

-- все продукты и их типы
select t.name, p.name, p.price from type t
inner join product p on t.id = p.type_id;
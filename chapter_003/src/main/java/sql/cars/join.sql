-- Создать структур данных в базе

create table corpus (
    id serial primary key,
    description varchar(100)
);

create table engine(
    id serial primary key,
    description varchar(100)
);

create table transmission(
    id serial primary key,
    description varchar(100)
);

create table car(
    id serial primary key,
    name varchar(100),
    corpus_id int references corpus(id),
    engine_id int references engine(id),
    transmission_id int references transmission(id)
);

insert into corpus(description) values
('Corpus 1'), ('Corpus 2'),('Corpus 3'),('Corpus 4'),('Corpus 5');

insert into engine(description) values
('Engine 1'), ('Engine 2'), ('Engine 3'), ('Engine 4'), ('Engine 5');

insert into transmission(description) values
('Tr 1'), ('Tr 2'), ('Tr 3'), ('Tr 4'), ('Tr 5');

insert into car(name, corpus_id, engine_id, transmission_id) values
('BMW', 1, 1, 1), ('MERSEDES', 2, 2, 2), ('PORSHE', 3, 3, 3), ('HAMMER', 4, 4, 4);

-- Вывести список всех машин и все привязанные к ним детали.

select car.name, c.description, e.description, t.description from car
join corpus c on car.corpus_id = c.id
join engine e on car.engine_id = e.id
join transmission t on car.transmission_id = t.id;

-- Вывести отдельно детали (1 деталь - 1 запрос), которые не используются в машине

select c2.id, c2.description  from car c
right join corpus c2 on c.corpus_id = c2.id
where c.id is null;

select e.id, e.description  from car c
right join engine e on c.engine_id = e.id
where c.id is null;

select t.id, t.description  from car c
right join transmission t on c.transmission_id = t.id
where c.id is null;
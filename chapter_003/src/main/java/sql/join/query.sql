-- 1. Создать таблицы и заполнить их начальными данными.

create table departments (
    id serial primary key,
    name varchar(50)
);

create table employees (
    id serial primary key,
    name varchar(50),
    department_id int references departments(id)
);

insert into departments(name) values
('Финансовый'),
('Логистический'),
('Кадровый'),
('Коммерческий');

INSERT INTO employees(name, department_id) values
('Василий', 1),
('Сергей', 2),
('Петр', 3),
('Антон', 2),
('Евгений', 1),
('Илья', 3),
('Юлия', null);

-- 2. Выполнить запросы с left, right, full, cross соединениями

select * from employees e
left join departments d on e.department_id = d.id;

select * from departments d
right join employees e on d.id = e.department_id;

select * from departments d
full join employees e on d.id = e.department_id;

select * from departments d
cross join employees e;

-- 3. Используя left join найти работников, которые не относятся ни к одну из департаментов.

select * from employees e
left join departments d on e.department_id = d.id
where d.id is null;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат.

select * from employees e
left join departments d on e.department_id = d.id;

select * from departments d
right join employees e on d.id = e.department_id;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
-- Используя cross join составить все возможные разнополые пары

create table teens(
    id serial primary key,
    name varchar(20),
    gender char(1)
);

insert into teens(name, gender) VALUES
('James', 'M'),
('Kate', 'F'),
('Tom', 'M'),
('Jessika', 'F'),
('Mike', 'M'),
('Mari', 'F');

select * from teens t1
cross join teens t2
where t1.gender != t2.gender;
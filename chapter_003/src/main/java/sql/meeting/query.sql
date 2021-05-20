CREATE TABLE meeting(
    id serial primary key,
    name varchar(100)
);

CREATE TABLE users(
    id serial primary key,
    name varchar(100)
);

CREATE TABLE status(
    id serial primary key,
    name varchar(20)
);

CREATE TABLE entry(
    id serial primary key,
    meeting_id int references meeting(id) not null,
    user_id int references users(id) not null,
    status_id int references status(id) not null
);

INSERT INTO meeting(name)
values('meeting 1'),
      ('meeting 2'),
      ('meeting 3'),
      ('meeting 4');

INSERT INTO users(name)
values('User 1'),
      ('User 2'),
      ('User 3'),
      ('User 4'),
      ('User 5'),
      ('User 6');

INSERT INTO status(name)
values('New'),
      ('Confirmed'),
      ('Declined');

INSERT INTO entry(meeting_id, user_id, status_id)
values(1, 1, 1),
      (1, 2, 2),
      (1, 3, 3),
      (2, 4, 1),
      (2, 5, 2);


-- запрос, который получит список всех заявок и количество подтвердивших участников
select m.name, count(e.meeting_id) from meeting m
inner join entry e on e.meeting_id = m.id
inner join status s on s.id = e.status_id and s.name = 'Confirmed'
group by m.name;

-- получить все совещания, где не было ни одной заявки на посещения
select m.name from meeting m
left join entry e on e.meeting_id = m.id
group by m.name
having count(e.meeting_id) = 0;
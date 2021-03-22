create table cars (
	id serial primary key,
	mark varchar(255),
	release_year int,
	description text
)

------------------

INSERT INTO cars (mark, release_year, description)
VALUES ('Chevrolet Lachetti', 2008, 'автомобиль компактного класса, созданный южнокорейским автопроизводителем GM Daewoo');

------------------

SELECT * FROM cars;

------------------

UPDATE cars SET release_year = 2009;

-----------------

SELECT * FROM cars;

-----------------

DELETE FROM cars;
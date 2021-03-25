insert into roles(name) values ('Client');
insert into roles(name) values ('Support');
insert into roles(name) values ('Admin');

insert into rights(name) values ('Add ticket');
insert into rights(name) values ('Answer in ticket');
insert into rights(name) values ('Close ticket');

insert into roles_rights(role_id, right_id) values (1, 1);
insert into roles_rights(role_id, right_id) values (1, 2);
insert into roles_rights(role_id, right_id) values (2, 1);
insert into roles_rights(role_id, right_id) values (2, 2);
insert into roles_rights(role_id, right_id) values (3, 1);
insert into roles_rights(role_id, right_id) values (3, 2);
insert into roles_rights(role_id, right_id) values (3, 3);

insert into users(name, role_id) values ('Ivan', 1);
insert into users(name, role_id) values ('Petr', 2);
insert into users(name, role_id) values ('Kirill', 3);

insert into states(name) values ('new');
insert into states(name) values ('in work');
insert into states(name) values ('closed');

insert into categories(name) values ('immediate');
insert into categories(name) values ('important');
insert into categories(name) values ('normal');

insert into items(description, user_id, state_id, category_id) values ('Server is down!', 1, 2, 1);
insert into items(description, user_id, state_id, category_id) values ('Service is not working properly', 2, 1, 2);
insert into items(description, user_id, state_id, category_id) values ('Need a new mouse', 1, 3, 3);

insert into comments(comment_text, item_id, user_id) values ('a quick lift doesnt count as a fall', 1, 3);
insert into comments(comment_text, item_id, user_id) values ('black or white?', 3, 2);

insert into attaches(definition, item_id) values ('attachment 1', 2);
insert into attaches(definition, item_id) values ('attachment 2', 2);
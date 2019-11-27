create database class_query;

create table roles (
		id serial primary key,
		role_name character varying (2000)
);

create table users (
		id serial primary key,
		user_name character varying (2000),
		role_id int references roles(id)
);

create table rules (
		id serial primary key,
		rule_name character varying (2000)
);

create table roles_rules (
		id serial primary key,
		role_id int references roles(id),
		rule_id int references rules(id)
);

create table category (
		id serial primary key,
		category character varying (2000)
);

create table state (
		id serial primary key,
		state character varying (2000)
);

create table items (
		id serial primary key,
		query_item character varying (2000),
		category_id int references category(id),
		state_id int references state(id)
);

create table attachs (
		id serial primary key,
		file character varying (2000),
		item_id int references items(id)
);

create table comments (
		id serial primary key,
		comment character varying (2000),
		item_id int references items(id)
);

insert into roles (role_name) values ('admin');
insert into roles (role_name) values ('user');
insert into users (user_name, role_id) values ('anton', 1);
insert into users (user_name, role_id) values ('vasia', 1);
insert into users (user_name, role_id) values ('kir', 2);
insert into rules (rule_name) values ('block_all');
insert into rules (rule_name) values ('allow_all');
insert into roles_rules (role_id, rule_id) values (1, 1);
insert into roles_rules (role_id, rule_id) values (1, 2);
insert into roles_rules (role_id, rule_id) values (2, 1);
insert into roles_rules (role_id, rule_id) values (2, 2);
insert into category (category) values ('very important');
insert into category (category) values ('low important');
insert into state (state) values ('active');
insert into state (state) values ('finished');
insert into items (query_item, category_id, state_id) values ('make task 345fd', 1, 1);
insert into items (query_item, category_id, state_id) values ('make task kmj22', 2, 1);
insert into items (query_item, category_id, state_id) values ('make task уке89', 1, 2);
insert into attachs (file, item_id) values ('file to attach', 2);
insert into comments (comment, item_id) values ('it should be done good', 1);
insert into comments (comment, item_id) values ('it almost ready', 2);
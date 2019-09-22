create table if not exists tracker_items (
    id serial primary key not null,
    name varchar(250),
    description text,
    created timestamp
                                         );
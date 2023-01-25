create table users (
    id bigint auto_increment,
    name varchar(100),
    primary key (id)
);

create table advertisements (
    id bigint auto_increment,
    user_id bigint,
    title varchar(100),
    primary key (id)
);
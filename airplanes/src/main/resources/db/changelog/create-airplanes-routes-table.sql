create table airplanes
(
    id            bigint auto_increment,
    airplane_type varchar(200),
    owner_airline varchar(200),
    primary key (id)
);

create table routes
(
    id             bigint auto_increment,
    departure_city varchar(255),
    arrival_city   varchar(255),
    date_of_flight date,
    airplane_id    bigint,
    primary key (id),
    foreign key (airplane_id) references airplanes (id)
);

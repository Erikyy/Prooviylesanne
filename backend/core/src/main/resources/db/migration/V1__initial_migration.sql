create table if not exists event (
                                     id serial not null primary key unique,
                                     name varchar(255) not null,
    date date,
    location varchar(255),
    info varchar(1000)
    );

create table if not exists citizen (
                                       id serial not null primary key unique,
                                       last_name varchar(255),
    id_number int not null default 00000000000,
    info varchar(1500)
    );

create table if not exists business (
                                        id serial not null primary key unique,
                                        reg_code int not null default 0,
                                        num_of_participants int not null default 1,
                                        info varchar(5000)
    );

create table if not exists participant (
                                           id serial not null primary key unique,
                                           event_id int,
                                           u_citizen_id int,
                                           u_business_id int,
                                           name varchar(255),
    payment_method char,
    constraint fk_event foreign key(event_id) references event(id) on delete cascade,

    unique(u_citizen_id),

    foreign key(u_citizen_id) references citizen(id),

    unique(u_business_id),
    foreign key(u_business_id) references business(id)
    );

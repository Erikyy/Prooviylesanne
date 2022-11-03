alter table evententity
rename to entities;

alter table citizenentity
rename to citizen;

alter table participantentity
rename to participant;

create table if not exists payment_method(
    p_m_id serial unique primary key not null,
    p_m_method varchar(100) not null
);

alter table participant
rename p_payment_method to p_payment_method_id;

alter table participant
add constraint p_payment_method_fk foreign key(p_payment_method_id) references payment_method(p_m_id);
alter table entities
rename to event;

alter table citizen
drop constraint participant_citizen;

alter table citizen
drop constraint citizenentity_c_participant_id_key;

alter table citizen
drop constraint citizenentity_pkey;

alter table citizen
add constraint citizen_pk primary key (c_id);

alter table citizen
add constraint citizen_participant_fk foreign key (c_participant_id) references participant(p_id);

alter table business
drop constraint participant_business;

alter table business
rename column c_business_id to b_participant_id;

alter table business
add constraint business_participant_fk foreign key (b_participant_id) references participant(p_id);

alter table citizen
add unique (c_participant_id);

alter table business
add unique (b_participant_id);

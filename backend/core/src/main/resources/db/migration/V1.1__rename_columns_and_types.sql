alter table event
rename id to e_id;

alter table event
rename name to e_name;

alter table event
rename date to e_date;

alter table event
rename location to e_location;

alter table event
rename info to e_info;

alter table citizen
rename id to c_id;

alter table citizen
rename id_number to c_id_number;

alter table citizen
rename info to c_info;

alter table citizen
rename last_name to c_last_name;

alter table business
rename id to b_id;

alter table business
rename info to b_info;

alter table business
rename num_of_participants to b_num_of_participants;

alter table business
rename reg_code to b_reg_code;

alter table participant
rename id to p_id;

alter table participant
rename event_id to p_event_id;

alter table participant
rename name to p_name;

alter table participant
rename payment_method to p_payment_method;

alter table participant
drop constraint fk_event;

alter table participant
add constraint fk_event foreign key(p_event_id) references event(e_id) on delete cascade;

alter table participant
drop constraint participant_u_business_id_fkey;

alter table participant
drop constraint participant_u_citizen_id_fkey;

alter table participant
drop constraint participant_u_citizen_id_key;

alter table participant
drop constraint participant_u_business_id_key;

alter table participant
add constraint p_citizen_fk foreign key(u_citizen_id) REFERENCES citizen(c_id) on delete cascade on update cascade;

alter table participant
add constraint unique_citizen unique(u_citizen_id);

alter table participant
add constraint p_business_fk foreign key(u_business_id) REFERENCES business(b_id) on delete cascade on update cascade;

alter table participant
add constraint unique_business unique(u_business_id);

alter table participant
alter column p_payment_method type varchar(50);
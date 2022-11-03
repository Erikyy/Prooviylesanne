alter table eventEntity
    rename id to e_id;

alter table eventEntity
    rename name to e_name;

alter table eventEntity
    rename date to e_date;

alter table eventEntity
    rename location to e_location;

alter table eventEntity
    rename info to e_info;

alter table citizenEntity
    rename id to c_id;

alter table citizenEntity
    rename id_number to c_id_number;

alter table citizenEntity
    rename info to c_info;

alter table citizenEntity
    rename last_name to c_last_name;

alter table business
    rename id to b_id;

alter table business
    rename info to b_info;

alter table business
    rename num_of_participants to b_num_of_participants;

alter table business
    rename reg_code to b_reg_code;

alter table participantEntity
    rename id to p_id;

alter table participantEntity
    rename event_id to p_event_id;

alter table participantEntity
    rename name to p_name;

alter table participantEntity
    rename payment_method to p_payment_method;

alter table participantEntity
    drop constraint fk_event;

alter table participantEntity
    add constraint fk_event foreign key(p_event_id) references eventEntity(e_id) on delete cascade;

alter table participantEntity
    add constraint p_citizen_fk foreign key(u_citizen_id) REFERENCES citizenEntity(c_id) on delete cascade on update cascade;

alter table participantEntity
    add constraint unique_citizen unique(u_citizen_id);

alter table participantEntity
    add constraint p_business_fk foreign key(u_business_id) REFERENCES business(b_id) on delete cascade on update cascade;

alter table participantEntity
    add constraint unique_business unique(u_business_id);

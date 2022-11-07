alter table participant
drop constraint fk_event;

alter table participant
drop column p_event_id;

create table event_participant (
    id serial primary key,
    event_id int,
    participant_id int,
    unique(event_id, participant_id),
    constraint event_sub_id_fk foreign key(event_id) references event (e_id),
    constraint participant_sub_id_fk foreign key(participant_id) references participant(p_id)
);
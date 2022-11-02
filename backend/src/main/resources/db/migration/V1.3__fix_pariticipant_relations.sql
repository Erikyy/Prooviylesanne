alter table participantEntity
drop constraint p_citizen_fk;

alter table participantEntity
drop constraint p_business_fk;

alter table participantEntity
drop constraint unique_business;

alter table participantEntity
drop constraint unique_citizen;

alter table participantEntity
drop column u_business_id;

alter table participantEntity
drop column u_citizen_id;

alter table citizenEntity
add column c_participant_id int unique;

alter table business
add column c_business_id int unique;

alter table citizenEntity
add constraint participant_citizen FOREIGN KEY(c_participant_id) references participantEntity (p_id);

alter table business
add constraint participant_business foreign key(c_business_id) references participantEntity (p_id);

# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups


create sequence s_contact_id;

create table contact (
  id                       bigint DEFAULT nextval('s_contact_id'),
  name                     varchar(255),
  telephone                varchar(255),
  constraint pk_scala primary key (id))
;






# --- !Downs

drop table contact;
drop sequence s_contact_id;



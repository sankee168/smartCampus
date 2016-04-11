# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table beacon (
  id                        integer auto_increment not null,
  description               varchar(255),
  constraint pk_beacon primary key (id))
;

create table beacon_category (
  beacon_id                 integer auto_increment not null,
  category                  varchar(255),
  constraint pk_beacon_category primary key (beacon_id))
;

create table beacon_location (
  beacon_id                 integer auto_increment not null,
  location                  varchar(255),
  constraint pk_beacon_location primary key (beacon_id))
;

create table category (
  id                        integer auto_increment not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_category primary key (id))
;

create table event (
  id                        integer auto_increment not null,
  name                      varchar(255),
  location                  varchar(255),
  start_time                date,
  end_time                  date,
  description               varchar(255),
  category                  varchar(255),
  external_link             varchar(255),
  is_active                 tinyint(1) default 0,
  beacon_id                 integer,
  constraint pk_event primary key (id))
;

create table location (
  id                        integer auto_increment not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_location primary key (id))
;

create table user (
  device_id                 integer auto_increment not null,
  user_name                 varchar(255),
  role                      varchar(255),
  categories                varchar(255),
  constraint pk_user primary key (device_id))
;

alter table event add constraint fk_event_beacon_1 foreign key (beacon_id) references beacon (id) on delete restrict on update restrict;
create index ix_event_beacon_1 on event (beacon_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table beacon;

drop table beacon_category;

drop table beacon_location;

drop table category;

drop table event;

drop table location;

drop table user;

SET FOREIGN_KEY_CHECKS=1;


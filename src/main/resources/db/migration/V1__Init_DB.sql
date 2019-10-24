create sequence hibernate_sequence start 1 increment 1;

create table car (
    license_plate varchar(25) not null,
    car_brand varchar(25),
    car_model varchar(25),
    owner_driver_id varchar(64),
primary key (license_plate));

create table driver (
    driver_id varchar(64) not null,
    first_name varchar(50),
    middle_name varchar(50),
    second_name varchar(50),
primary key (driver_id));

create table fine (
    fine_id int8 not null,
    fine_description varchar(255),
    fine_tax int4 not null,
primary key (fine_id));

create table fine_history (
    id int8 not null,
    car_license_plate varchar(25),
    fine_fine_id int8,
primary key (id));

alter table if exists car add constraint car_owner_fk foreign key (owner_driver_id) references driver;

alter table if exists fine_history add constraint fine_history_car_fk foreign key (car_license_plate) references car;

alter table if exists fine_history add constraint fine_history_fine_fk foreign key (fine_fine_id) references fine;
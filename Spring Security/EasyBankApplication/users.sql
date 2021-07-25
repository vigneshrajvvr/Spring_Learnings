drop database if exists easybank;
create database easybank;

use easybank;

drop table if exists users;
create table users(
id int not null auto_increment,
username varchar(45) not null,
password varchar(45) not null,
enabled int not null,
primary key (id)
);

drop table if exists authorities;
create table authorities(
id int  not null,
username varchar(45) not null, 
authority varchar(45) not null,
primary key (id)
);

insert into users values(1, 'happy', '12345', null);
insert into authorities values(1, 'happy', 'write');
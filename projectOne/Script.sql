create table if not exists reimb_form (
  reimb_id serial primary key,
  employee_id int4 not null,
  employee_name varchar(20) not null,
  expense_date varchar(10) not null,
  description varchar(255),
  amount int4 not null,
  request_date varchar(10) not null
);

create table if not exists status (
  status_id serial primary key,
  request_id int4 not null,
  requester varchar(20) not null,
  amount int4 not null,
  status varchar(8) not null,
  manager_comment varchar(255),
  manager_name varchar(20) not null,
  decision_date varchar(10) not null
);

create table if not exists users (
 user_id serial primary key,
 username varchar(20) not null unique,
 employee_name varchar(30) not null unique,
 role varchar default 'ROLE_USER',
 email varchar(40) not null unique 
)

create table if not exists user_login (
  id serial primary key,
  username varchar(20) not null unique,
  first_name varchar(20) not null,
  last_name varchar(20) not null,
  passwords varchar not null,
  roles varchar(8) not null check (roles = any('{EMPLOYEE, MANAGER}'::text[])),
  email varchar(40) not null unique
)

insert into user_login (username, first_name, last_name, passwords, roles, email) values 
  ('jiaying.li', 'jia ying', 'li', 123456, 'EMPLOYEE', 'jiaying.li@revature.com');
  
insert into user_login (username, first_name, last_name, passwords, roles, email) values 
  ('august.duet', 'August', 'Duet', 123456, 'MANAGER', 'august.duet@revature.com');
  
insert into users (username, employee_name, role, email) values ('jiaying.li', 'Jia ying Li', 'employee', 'jiaying.li@revature.net');
create table users
(
    id bigserial primary key ,
    first_name varchar(255),
    last_name varchar(255),
    birth_place text,
    birth_date date,
    created_at timestamp,
    budget numeric(16,2)
);

alter table users
    add column age int;

alter table users
    rename column age to age_in_years;

alter table users
    drop column age_in_years;

comment on table users
    is 'divide first name and last name';


insert into users values ('Kanan', 'Orujov', 'Azerbaijan', '2004-05-19', '2024-06-19', 19.00);
insert into users values (2, 'Test1', 'Test', 'Russia', '2014-07-12', '2024-06-19', 119.00);
insert into users values (3, 'Example', 'Example', 'USA', '2009-11-29', '2024-06-19', 32.00);


update users
    set budget = 25.19
    where first_name = 'Test';

select * from users;

select first_name, budget from users;

delete from users
    where first_name = 'Test1';

select * from users
    where budget between 20 and 60;

select * from users order by budget desc;

insert into users values (4, 'Kanan', 'Orujov', 'Azerbaijan', '2004-05-19', '2024-06-19', 19.00);

select distinct first_name from users;
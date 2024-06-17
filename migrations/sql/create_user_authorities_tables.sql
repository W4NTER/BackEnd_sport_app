create table authorities
(
    username varchar not null ,
    authority varchar not null
);

create table users
(
    user_id bigint generated always as identity,
    username varchar not null ,
    password varchar not null ,
    enabled varchar not null
);
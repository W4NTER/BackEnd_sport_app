create table if not exists playgrounds(
    playground_id bigint generated always as identity,
    latitude float not null,
    longitude float not null,
    path_to_image varchar,
    price bigint not null,
    sport varchar not null,
    city varchar not null,
    rating bigint,
    address varchar not null
);
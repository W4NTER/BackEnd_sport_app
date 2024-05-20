create table if not exists events_to_users(
    user_id bigint not null,
    event_id bigint not null
);

create table if not exists events(
    event_id bigint generated always as identity,
    title varchar not null,
    body text,
    number_of_participants bigint,
    city varchar not null,
    rating bigint not null,
    participants_level varchar not null,
    start_time timestamp with time zone not null,
    end_time timestamp with time zone not null,
    price bigint not null,
    playground_id bigint not null
);
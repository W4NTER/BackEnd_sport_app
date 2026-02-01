create table if not exists teams_to_users(
    user_id bigint not null,
    team_id bigint not null
);

create table if not exists teams(
    team_id bigint generated always as identity,
    sport varchar not null,
    count_teammates bigint not null,
    team_level varchar not null,
    title varchar not null,
    description text
);
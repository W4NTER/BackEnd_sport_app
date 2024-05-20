create table if not exists profile(
    user_id bigint not null,
    height bigint,
    weight bigint,
    city varchar
)
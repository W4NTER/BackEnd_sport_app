-- alter table events add column author_id bigint not null;
-- alter table teams add column author_id bigint not null;

-- alter table teams add column title varchar;

-- alter table events add column sport varchar;
alter table events add column max_participants bigint;

alter table teams add column max_count_teammates bigint;
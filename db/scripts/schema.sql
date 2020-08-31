create table sites (
  id serial primary key,
  name varchar(200) unique,
  password varchar(200),
  login varchar(200) unique
);
create table links (
  id serial primary key,
  origin_url varchar(200) unique,
  short_cut varchar(200) unique,
  site_id int references sites(id),
  requests_count int DEFAULT 0 NOT NULL
);

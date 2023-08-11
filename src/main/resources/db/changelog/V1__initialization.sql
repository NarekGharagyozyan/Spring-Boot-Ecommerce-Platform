create table if not exists products
(
    id              serial primary key,
    created_at      timestamp,
    updated_at      timestamp,
    category        varchar(255)     not null,
    company         varchar(255)     not null,
    count           integer          not null,
    is_in_deadline  boolean,
    name            varchar(255)     not null,
    price           double precision not null,
    production_date date             not null
);

create table if not exists roles
(
    id   serial primary key,
    role varchar(255) not null
        constraint uk_g50w4r0ru3g9uf6i6fr4kpro8
            unique
);

create table if not exists users
(
    id          serial primary key,
    created_at  timestamp,
    updated_at  timestamp,
    age         integer      not null,
    code        varchar(255),
    date        date         not null,
    email       varchar(255) not null
        constraint uk_6dotkott2kjsp8vw4d0m25fb7
            unique,
    gender      varchar(255),
    is_verified boolean,
    last_name   varchar(255) not null,
    middle_name varchar(255),
    name        varchar(255) not null,
    password    varchar(255) not null,
    phone       varchar(255) not null
        constraint uk_du5v5sr43g5bfnji4vb8hg5s3
            unique,
    username    varchar(255) not null
        constraint uk_r43af9ap4edm43mmtq01oddj6
            unique,
    role_id     integer      not null
        constraint fkp56c1712k691lhsyewcssf40f
            references roles
);

create table if not exists access_token
(
    id      serial primary key,
    token   varchar(255) not null
        constraint uk_1djybee0iap4odfl91gkxoxem
            unique,
    user_id integer      not null
        constraint fk35enf10xnmb70oiu2rk91nchy
            references users
);

create table if not exists products
(
    id serial primary key,
    created_at      timestamp,
    updated_at      timestamp,
    category        varchar(255)     not null,
    company         varchar(255)     not null,
    count           integer          not null,
    is_in_deadline  boolean,
    name            varchar(255)     not null,
    price           numeric(19, 2)  not null,
    production_date date             not null
);


create table if not exists roles
(
    id serial primary key,
    role varchar(255) not null
        constraint uk_g50w4r0ru3g9uf6i6fr4kpro8
            unique
);


create table if not exists users
(
    id serial primary key,
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
    id serial primary key,
    token   varchar(255) not null
        constraint uk_1djybee0iap4odfl91gkxoxem
            unique,
    user_id integer      not null
        constraint fk35enf10xnmb70oiu2rk91nchy
            references users
);


create table if not exists basket
(
    id serial primary key,
    count      integer not null,
    product_id integer not null
        constraint fkpsbpg5lc7uk06uixvl9ehdkvt
            references products,
    user_id    integer not null
        constraint fk810a8gq30miyp6j1eub97qm6k
            references users
);


create table if not exists orders
(
    id serial primary key,
    created_at   timestamp,
    updated_at   timestamp,
    note         varchar(255),
    status       varchar(255)   not null,
    total_amount numeric(19, 2) not null,
    user_id      integer        not null
        constraint fk32ql8ubntj5uh44ph9659tiih
            references users
);

create table if not exists order_item
(
    id serial primary key,
    created_at  timestamp,
    updated_at  timestamp,
    count       integer        not null,
    product_details jsonb,
    total_price numeric(19, 2) not null,
    order_id    integer        not null
        constraint fkt4dc2r9nbvbujrljv3e23iibt
            references orders
);

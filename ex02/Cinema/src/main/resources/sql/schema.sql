create table if not exists file_info
(
    id   bigserial  primary key,
    uuid uuid         not null  constraint uk_7xq0aoih3mc8441slevo9s8al unique,
    name varchar(255) not null,
    size bigint       not null,
    type varchar(255) not null
);

create table if not exists hall
(
    id         bigserial    primary key,
    uuid       uuid    not null constraint uk_b96da937q4c6q385q3gxyhh5x unique,
    seatscount integer not null
);

create table if not exists film
(
    id              bigserial   primary key,
    uuid            uuid         not null   constraint uk_h3a96js8q2yif6cf2669p24ib unique,
    agerestrictions smallint     not null,
    description     varchar(1000),
    duration        integer      not null,
    title           varchar(100) not null   constraint uk_lmfyujjg907qk2l0rhh2eeurc unique,
    yearofrelease   integer      not null,
    poster_file_id  bigint  constraint uk_h5qsf2twxj0cpwijuaspint1i unique  constraint fktaf5txt1afv7xf216satf3tc7  references file_info
);

create table if not exists film_session
(
    id                  bigserial   primary key,
    uuid                uuid      not null  constraint uk_k89fhtt5xh77mk0eropnghklu unique,
    sessiondatetimefrom timestamp not null,
    sessiondatetimeto   timestamp not null,
    ticketcost          integer   not null,
    film_id             bigint    not null  constraint fk2fmwlpkvpykb97r67akgw41xf  references film,
    hall_id             bigint    not null  constraint fknfyvpd54kpjqnvpu20u92j12s  references hall
);

create table if not exists user_account
(
    id   bigserial  primary key,
    uuid uuid not null  constraint uk_piri22wyjhfc7odv6x0eyr7i6 unique
);

create table if not exists message
(
    id             bigserial    primary key,
    uuid           uuid         not null    constraint uk_jpgbu1b7sujc202dofmlsi8id unique,
    datetimecreate timestamp    not null,
    text           varchar(255) not null,
    author_user_id bigint       not null    constraint fk8ni5vpmc6bdaxvw2f61jm16ir  references user_account,
    film_id        bigint       not null    constraint fk36i33m3j60qqbmew12hgbo4q7  references film
);


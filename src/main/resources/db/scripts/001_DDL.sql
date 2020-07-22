create sequence hibernate_sequence start with 100 increment by 1;

create table address
(
    id       bigint       not null,
    name     varchar(255) not null,
    owner_id integer,
    constraint ADDRESS_PK primary key (id)
);

create table client
(
    id      integer      not null,
    name    varchar(255) not null,
    surname varchar(255) not null,
    constraint CLIENT_PK primary key (id),
    constraint UK_NAME_SU unique (name,surname)
);

alter table address
    add constraint ADDRESS_OWNER_FK foreign key (owner_id) references client;

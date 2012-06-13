
    drop table if exists member_group;

    create table member_group (
        name varchar(255) not null unique,
        primary key (name)
    ) ;

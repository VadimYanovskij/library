create table author
(
    id        int auto_increment,
    firstname varchar(45) not null,
    lastname  varchar(45) not null,
    constraint id_UNIQUE
        unique (id)
);

alter table author
    add primary key (id);

create table blocked_token
(
    id varchar(256) not null,
    constraint id_UNIQUE
        unique (id)
);

alter table blocked_token
    add primary key (id);

create table book_status
(
    id               int auto_increment,
    book_status_name varchar(45) not null,
    constraint book_status_name_UNIQUE
        unique (book_status_name),
    constraint id_UNIQUE
        unique (id)
);

alter table book_status
    add primary key (id);

create table category
(
    id            int auto_increment,
    parent_id     int         null,
    category_name varchar(45) not null,
    constraint id_UNIQUE
        unique (id)
);

alter table category
    add primary key (id);

create table publisher
(
    id             int auto_increment,
    publisher_name varchar(45) not null,
    publisher_city varchar(45) not null,
    constraint id_UNIQUE
        unique (id)
);

alter table publisher
    add primary key (id);

create table book
(
    id               int auto_increment,
    book_name        varchar(100) not null,
    publisher_id     int          null,
    publication_year year         not null,
    category_id      int          null,
    book_status_id   int          null,
    constraint id_UNIQUE
        unique (id),
    constraint fk_book_book_status
        foreign key (book_status_id) references book_status (id)
            on delete set null,
    constraint fk_book_category
        foreign key (category_id) references category (id)
            on delete set null,
    constraint fk_book_publisher
        foreign key (publisher_id) references publisher (id)
            on delete set null
);

create index fk_book_book_status_idx
    on book (book_status_id);

create index fk_book_category_idx
    on book (category_id);

create index fk_book_publisher_idx
    on book (publisher_id);

alter table book
    add primary key (id);

create table author_book
(
    author_id int null,
    book_id   int null,
    constraint fk_author_book_author
        foreign key (author_id) references author (id)
            on delete set null,
    constraint fk_author_book_book
        foreign key (book_id) references book (id)
            on delete set null
);

create index fk_author_book_author_idx
    on author_book (author_id);

create index fk_author_book_book_idx
    on author_book (book_id);

create table role
(
    id        int auto_increment,
    role_name varchar(45) not null,
    constraint id_UNIQUE
        unique (id),
    constraint role_name_UNIQUE
        unique (role_name)
);

alter table role
    add primary key (id);

create table user
(
    id        int auto_increment,
    username  varchar(45)  not null,
    email     varchar(64)  null,
    password  varchar(256) not null,
    firstname varchar(45)  null,
    lastname  varchar(45)  null,
    birthday  date         null,
    constraint email_UNIQUE
        unique (email),
    constraint id_UNIQUE
        unique (id),
    constraint username_UNIQUE
        unique (username)
);

alter table user
    add primary key (id);

create table borrow
(
    id             int auto_increment,
    user_id        int  null,
    book_id        int  null,
    borrow_date    date not null,
    repayment_date date not null,
    return_date    date null,
    constraint id_UNIQUE
        unique (id),
    constraint fk_borrow_book
        foreign key (book_id) references book (id)
            on delete set null,
    constraint fk_borrow_user
        foreign key (user_id) references user (id)
            on delete set null
);

create index fk_borrow_book_idx
    on borrow (book_id);

create index fk_borrow_user_idx
    on borrow (user_id);

alter table borrow
    add primary key (id);

create table user_role
(
    role_id int null,
    user_id int null,
    constraint fk_user_role_role
        foreign key (role_id) references role (id)
            on delete set null,
    constraint fk_user_role_user
        foreign key (user_id) references user (id)
            on delete set null
);

create index fk_user_role_role_idx
    on user_role (role_id);

create index fk_user_role_user_idx
    on user_role (user_id);


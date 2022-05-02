create table menu
(
    id            varbinary(16)  not null,
    displayed     bit            not null,
    name          varchar(255)   not null,
    price         decimal(19, 2) not null,
    menu_group_id varbinary(16)  not null,
    primary key (id)
) engine = InnoDB;

create table menu_group
(
    id   varbinary(16) not null,
    name varchar(255)  not null,
    primary key (id)
) engine = InnoDB;

create table menu_product
(
    seq        bigint        not null auto_increment,
    quantity   bigint        not null,
    product_id varbinary(16) not null,
    menu_id    varbinary(16) not null,
    primary key (seq)
) engine = InnoDB;

create table delivery_order_line_item
(
    seq      bigint        not null auto_increment,
    quantity bigint        not null,
    menu_id  varbinary(16) not null,
    order_id varbinary(16) not null,
    primary key (seq)
) engine = InnoDB;

create table eatin_order_line_item
(
    seq      bigint        not null auto_increment,
    quantity bigint        not null,
    menu_id  varbinary(16) not null,
    order_id varbinary(16) not null,
    primary key (seq)
) engine = InnoDB;

create table takeout_order_line_item
(
    seq      bigint        not null auto_increment,
    quantity bigint        not null,
    menu_id  varbinary(16) not null,
    order_id varbinary(16) not null,
    primary key (seq)
) engine = InnoDB;

create table order_table
(
    id               varbinary(16) not null,
    empty            bit           not null,
    name             varchar(255)  not null,
    number_of_guests integer       not null,
    primary key (id)
) engine = InnoDB;

create table delivery_orders
(
    id               varbinary(16) not null,
    delivery_address varchar(255),
    order_date_time  datetime(6)   not null,
    status           varchar(255)  not null,
    type             varchar(255)  not null,
    order_table_id   varbinary(16),
    primary key (id)
) engine = InnoDB;

create table eatin_orders
(
    id               varbinary(16) not null,
    delivery_address varchar(255),
    order_date_time  datetime(6)   not null,
    status           varchar(255)  not null,
    type             varchar(255)  not null,
    order_table_id   varbinary(16),
    primary key (id)
) engine = InnoDB;

create table takeout_orders
(
    id               varbinary(16) not null,
    delivery_address varchar(255),
    order_date_time  datetime(6)   not null,
    status           varchar(255)  not null,
    type             varchar(255)  not null,
    order_table_id   varbinary(16),
    primary key (id)
) engine = InnoDB;

create table product
(
    id    varbinary(16)  not null,
    name  varchar(255)   not null,
    price decimal(19, 2) not null,
    primary key (id)
) engine = InnoDB;

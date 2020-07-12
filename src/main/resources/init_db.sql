drop table if exists Operation;

drop table if exists  Operation_Type;

drop table if exists  Product;

drop table if exists  Product_Slot;

drop table if exists  "User";

drop table if exists  User_Role;

/*==============================================================*/
/* Table: Operation                                             */
/*==============================================================*/
create table Operation (
                           id                   SERIAL                 not null,
                           operation_type_id    INT4                 not null,
                           slot_id              INT4                 not null,
                           product_id           INT4                 not null,
                           operation_time       TIMESTAMP            not null,
                           quantity             INT4                 not null,
                           constraint PK_OPERATION primary key (id)
);

/*==============================================================*/
/* Table: Operation_Type                                        */
/*==============================================================*/
create table Operation_Type (
                                id                   INT4                 not null,
                                operation_name       VARCHAR(32)          not null,
                                constraint PK_OPERATION_TYPE primary key (id)
);

/*==============================================================*/
/* Table: Product                                               */
/*==============================================================*/
create table Product (
                         id                   SERIAL                 not null,
                         product_name         VARCHAR(256)         not null,
                         price                FLOAT8               not null,
                         weight               FLOAT8               not null,
                         constraint PK_PRODUCT primary key (id)
);

/*==============================================================*/
/* Table: Product_Slot                                          */
/*==============================================================*/
create table Product_Slot (
                              id                   SERIAL                 not null,
                              slot_name            VARCHAR(32)         not null,
                              shelf                INT4                 null,
                              curr_product_id      INT4                 null,
                              constraint PK_PRODUCT_SLOT primary key (id)
);

/*==============================================================*/
/* Table: "User"                                                */
/*==============================================================*/
create table "User" (
                        id                   SERIAL                 not null,
                        username             VARCHAR(128)         not null,
                        password             VARCHAR(60)          not null,
                        role_id              INT4                 not null,
                        constraint PK_USER primary key (id)
);

/*==============================================================*/
/* Table: User_Role                                             */
/*==============================================================*/
create table User_Role (
                           id                   INT4                 not null,
                           role_name            VARCHAR(64)          not null,
                           constraint PK_USER_ROLE primary key (id)
);

alter table Operation
    add constraint FK_OPERATION_TYPE foreign key (operation_type_id)
        references Operation_Type (id)
        on delete restrict on update restrict;

alter table Operation
    add constraint FK_OPERATION_PRODUCT foreign key (product_id)
        references Product (id)
        on delete restrict on update restrict;

alter table Operation
    add constraint FK_OPERATION_SLOT foreign key (slot_id)
        references Product_Slot (id)
        on delete restrict on update restrict;

alter table "User"
    add constraint FK_USER_ROLE foreign key (role_id)
        references User_Role (id)
        on delete restrict on update restrict;

INSERT INTO operation_type(id, operation_name) VALUES (1, 'ADD');
INSERT INTO operation_type(id, operation_name) VALUES (2, 'RETRIEVE');
INSERT INTO operation_type(id, operation_name) VALUES (3, 'PURCHASE');
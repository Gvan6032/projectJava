/*use book;

create table books (
id int not null auto_increment,
nameBook varchar(50) not null,
authorBook varchar(50) not null,
datePublication date not null,
descriptionBook varchar(100) not null,
priceBook varchar(50) not null,
primary key (id));*/

/*insert into books (nameBook,authorBook,datePublication,descriptionBook,priceBook)
values ("Winnie-the-Pooh","Faster H.H.",now(),"The adventures of Winnie-the-Pooh","21.09");*/

/*select * from books;*/

/*use book;

create table books (
id int not null auto_increment,
title varchar(50) not null,
author varchar(50) not null,
price varchar(50) not null,
currency varchar(10) not null,
description varchar(100) not null,
primary key (id));*/


/*create database books;*/

/*use books;

create table users (
        id int not null auto_increment,
        userName varchar(20) not null,
        active bit not null,
        password varchar(20) not null,
        userRole varchar(20) not null,
        primary key (id)
    );
 
    create table orderDetails (
        id varchar(50) not null,
        amount double precision not null,
        price double precision not null,
        quanity integer not null,
        orderId varchar(50) not null,
        bookId varchar(20) not null,
        primary key (id)
    );
 
    create table orders (
        id varchar(50) not null,
        amount double precision not null,
        customerAddress varchar(255) not null,
        customerEmail varchar(128) not null,
        customerName varchar(255) not null,
        customerPhone varchar(128) not null,
        orderDate datetime not null,
        orderNum integer not null,
        primary key (ID)
    );
 orderdetails
    create table books (
        id varchar(20) not null,
        createDate datetime not null,
        image longblob,
		nameBook varchar(255) null,
        author varchar(50) null,
        description varchar(255) null,
        priceBook double precision not null,
        primary key (id)
    );
 
    /*alter table orders
        add constraint UK_sxhpvsj665kmi4f7jdu9d2791  unique (orderNum);*/
 
  /*  alter table orderDetails
        add constraint ORDER_DETAIL_ORD_FK
        foreign key (orderId)
        references orders (id);
 
    alter table orderDetails
        add constraint ORDER_DETAIL_PROD_FK
        foreign key (bookId)
        references books (id);

insert into users (userName, active, password, userRole)
values ('anya1', 1, '123', 'EMPLOYEE');
 
insert into users (userName, active, password, userRole)
values ('manager1', 1, '123', 'MANAGER');
        
insert into books (id,nameBook,author, description,priceBook,createDate)
values ('001', 'A Patchwork Family','Cath Bramley','FeelGood of the month', 10, current_timestamp());
 
insert into books (id,nameBook,author, description,priceBook,createDate)
values ('002', 'The Decent Inn of Death','Rennie Airth','Mystery of the month', 50, current_timestamp() );
 
insert into books (id,nameBook,author, description,priceBook,createDate)
values ('003', 'Goods of Jade and Shadow','Silvia Moreno-Garcia','Fantasy of the month', 105, current_timestamp() );
 
insert into books  (id,nameBook,author, description,priceBook,createDate)
values ('004', 'Today I Am Carey Paperback','Martin L. Shoemaker','Sci-Fi of the month', 98, current_timestamp() );*/ 


/*use books;
alter table orders add column orderStatus int(2) null;*/

/*select * from orders;
select * from users;
 
create table users (
        id int not null auto_increment,
        userName varchar(20) not null,
        active bit not null,
        password varchar(20) not null,
        userRole varchar(20) not null,
        primary key (id)
    );

insert into users(userName,active,password,userRole)
values("EMPLOYEE",0,"123456","EMPLOYEE");

insert into users(userName,active,password,userRole)
values("ADMIN",0,"admin123456","ADMIN");*/



create table Member_tbl
(
    id     number primary key,
    username   nvarchar2(30),
    password  nvarchar2(50)
);
create sequence member_seq start with 1 increment by 1;

create table Book_tbl
(id number primary key,
 nameBook nvarchar2(30),
 authorBook nvarchar2(30)
);
create sequence Book_seq start with 1 increment by 1;

insert into BOOK_TBL (id, nameBook, authorBook)
values (BOOK_SEQ.nextval,'Happy place','Emily Henry');
insert into BOOK_TBL (id, nameBook, authorBook)
values (BOOK_SEQ.nextval,'The 48 Laws of Power','Robert Greene');
insert into BOOK_TBL (id, nameBook, authorBook)
values (BOOK_SEQ.nextval,'Love Story','Eric Segal');
insert into BOOK_TBL (id, nameBook, authorBook)
values (BOOK_SEQ.nextval,'The Prince and the Pauper','Mark Twain');
insert into BOOK_TBL (id, nameBook, authorBook)
values (BOOK_SEQ.nextval,'The Inheritance Games','Jennifer Lynn Barnes');
commit ;

create table Borrow_tbl
(
id  number primary key,
username nvarchar2(30),
nameBook nvarchar2(30),
authorBook nvarchar2(30)
);
create sequence Borrow_seq start with 1 increment by 1;

create table suggestion_tbl
(
id  number primary key,
suggestion  nvarchar2(50)
);
create sequence suggestion_seq start with 1 increment by 1;

create table log_tbl(
id number,
class_name nvarchar2(50),
log_type nvarchar2(50),
data nvarchar2(50)
);
create sequence log_seq start with  1 increment by 1;


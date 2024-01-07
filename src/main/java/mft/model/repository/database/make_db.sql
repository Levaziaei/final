create table Member_tbl
(
    id     number primary key,
    username   nvarchar2(30),
    password  nvarchar2(50)
);
create sequence member_seq start with 1 increment by 1;

create table Borrow_tbl
(
    id number primary key,
    nameBook  nvarchar2(50),
    authorBook  nvarchar2(50),
    nameAndFamily  nvarchar2(50),
    yourSuggestion nvarchar2(100)
);
create sequence Borrow_seq start with 1 increment by 1;

create table Librarian_tbl
(
    id number primary key,
    nameAndFamily nvarchar2(50),
    nameBook  nvarchar2(50),
    search nvarchar2(50)
);
create sequence Librarian_seq start with 1 increment by 1;

create table AccountStudent
(
id number primary key,
nameAndFamily references Borrow_tbl,
Borrow references Borrow_tbl,
yourSuggestion references Borrow_tbl
);
create sequence AccountStudent_seq start with 1 increment by 1;

create table AccountStaff
(
    id references Borrow_tbl,
    nameAndFamily references Borrow_tbl,
    nameBook references Borrow_tbl,
    yourSuggestion references Borrow_tbl,
    addSuggestion nvarchar2(50)
);
create sequence AccountStaff_seq start with 1 increment by 1;






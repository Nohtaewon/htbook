create table "ORDER"(
    order_id varchar2(50) primary key,
    addressee varchar2(50) not null,
    member_id varchar2(50),
    member_addr1 varchar2(100) not null,
    member_addr2 varchar2(100) not null,
    member_addr3 varchar2(100) not null,
    order_state varchar2(30) not null,
    delivery_cost number not null,
    use_point number not null,
    order_date date default sysdate,
    FOREIGN KEY (member_id)REFERENCES book_member(member_id)
);

create sequence orderItemSeq increment by 1 start with 1 nomaxvalue nocycle nocache;

create table orderItem(
    order_item_id number primary key,
    order_id varchar2(50),
    bookId number,
    book_count number not null,
    bookPrice number not null,
    bookDiscount number not null,
    save_point number not null,
    FOREIGN KEY (order_id) REFERENCES "ORDER"(order_id),
    FOREIGN KEY (bookId) REFERENCES book(bookId)
);
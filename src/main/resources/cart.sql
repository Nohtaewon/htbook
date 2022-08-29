-- Oracle
create sequence cartSeq increment by 1 start with 1 nomaxvalue nocycle nocache;

create table cart(
    cart_id number primary key,
    member_id varchar2(50),
    bookId number,
    book_count number,
    foreign key (member_id) references book_member(member_id),
    foreign key (bookId) references book(bookId)
);

-- 유니크 제약조건 걸기
alter table cart add unique (member_id, bookId);
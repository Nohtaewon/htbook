-- 국가 테이블 생성
create table nation(
   nation_id varchar2(2) primary key,
   nation_name varchar2(50)
);
 
-- 국가 테이블 데이터 삽입
insert into nation values ('01', '국내');
insert into nation values ('02', '국외');

create sequence author_seq start with 1 increment by 1;

-- 작가 테이블 생성
create table author(
    author_id int primary key,
    author_name varchar2(50),
    nation_id varchar2(2),
    author_intro long,
    reg_date date default sysdate,
    update_date date default sysdate,
    foreign key (nation_id) references nation(nation_id)
);

insert into author(author_id, author_name, nation_id, author_intro) values(author_seq.nextval, '유홍준', '01', '작가 소개입니다' );
insert into author(author_id, author_name, nation_id, author_intro) values(author_seq.nextval, '김난도', '01', '작가 소개입니다' );
insert into author(author_id, author_name, nation_id, author_intro) values(author_seq.nextval, '폴크루그먼', '02', '작가 소개입니다' );


commit;
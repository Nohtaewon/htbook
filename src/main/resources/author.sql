drop table nation;
drop table author;
drop sequence authorSeq;

-- 국가 테이블 생성
create table nation(
   nationId varchar2(2) primary key,
   nationName varchar2(50)
);
 
-- 국가 테이블 데이터 삽입
insert into nation values ('01', '국내');
insert into nation values ('02', '국외');

create sequence authorSeq increment by 1 start with 1 nomaxvalue nocycle nocache;

-- 작가 테이블 생성
create table author(
    authorId number primary key,
    authorName varchar2(50),
    nationId varchar2(2),
    authorIntro varchar2(2000),
    regDate date default sysdate,
    updateDate date default sysdate,
    foreign key (nationId) references nation(nationId)
);


insert into author(authorId) values(authorSeq.NEXTVAL);

select authorSeq.currval from dual;

insert into author(authorId, authorName, nationId, authorIntro) values(authorSeq.nextval, '유홍준', '01', '작가 소개입니다' );
insert into author(authorId, authorName, nationId, authorIntro) values(authorSeq.nextval, '김난도', '01', '작가 소개입니다' );
insert into author(authorId, authorName, nationId, authorIntro) values(authorSeq.nextval, '폴크루그먼', '02', '작가 소개입니다' );
insert into author(authorId, authorName, nationId, authorIntro) values(authorSeq.nextval, 'test', '02', 'test' );

-- 인덱스명 찾기
select * from user_indexes;
commit;
CREATE TABLE BOOK_MEMBER(
  member_id VARCHAR2(50),
  member_pw VARCHAR2(100) NOT NULL,
  member_name VARCHAR2(30) NOT NULL,
  member_mail VARCHAR2(100) NOT NULL,
  member_addr1 VARCHAR2(100) NOT NULL,
  member_addr2 VARCHAR2(100) NOT NULL,
  member_addr3 VARCHAR2(100) NOT NULL,
  admin_ck NUMBER NOT NULL,
  reg_date DATE NOT NULL,
  money number NOT NULL,
  point number NOT NULL,
  PRIMARY KEY(member_id)
);
commit;

-- insert 테스트
insert into book_member values('admin23', 'admin', 'admin', 'admin', 'admin', 'admin', 'admin', 1, sysdate, 1000000, 1000000);
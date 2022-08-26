package com.ht.member.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberVO {

	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_mail;
	private String member_addr1;
	private String member_addr2;
	private String member_addr3;
	private int admin_ck;
	private int money;
	private int point;
	private Date reg_date;
}

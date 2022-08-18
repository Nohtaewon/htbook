package com.ht.author.domain;

import java.util.Date;

import lombok.Data;

// author 테이블에 데이터를 전달하거나, 테이블로부터 반환받은 데이터를 담을 객체를 정의하는 클래스
@Data
public class AuthorVO {
	
	private int authorId;
	private String authorName;
	private String nationId;
	private String nationName;
	private String authorIntro;
	private Date regDate;
	private Date updateDate;
}

package com.ht.book.domain;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CateFilterDTO {

	// 카테고리 이름
	private String cateName;
	
	// 카테고리 넘버
	private String cateCode;
	
	// 카테고리 상품 수
	private int cateCount;
	
	// 국내, 국외 분류
	private String cateGroup;

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
		this.cateGroup = cateCode.split("")[0];
	}

	public void setCateCount(int cateCount) {
		this.cateCount = cateCount;
	}

	public void setCateGroup(String cateGroup) {
		this.cateGroup = cateGroup;
	}
	
	
}

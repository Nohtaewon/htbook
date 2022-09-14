package com.ht.order.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class OrderCancelDTO {
	
	// 주문 취소에 필요한 key
	@Getter @Setter
	private String member_id;
	@Getter @Setter
	private String order_id;
	
	// 주문 취소후 사용자가 있던 페이지로 리다이렉트 하기위한 페이지 정보
	@Getter @Setter
	private String keyword;
	@Getter @Setter
	private int amount;	
	@Getter @Setter
	private int pageNum;
}

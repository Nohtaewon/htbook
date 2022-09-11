package com.ht.order.domain;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class OrderDTO {
	@Getter@Setter
	private String order_id;
	@Getter@Setter
	private String addressee;
	@Getter@Setter
	private String member_id;
	@Getter@Setter
	private String member_addr1;
	@Getter@Setter
	private String member_addr2;
	@Getter@Setter
	private String member_addr3;
	@Getter@Setter
	private String order_state;
	@Getter@Setter
	private List<OrderItemDTO> orders;	
	@Getter@Setter
	private int delivery_cost;
	@Getter@Setter
	private int use_point;
	@Getter@Setter
	private Date order_date;
	
	// DB테이블 존재 하지 않는 데이터
	@Getter@Setter
	private int order_sale_price;
	@Getter@Setter
	private int order_save_point;
	@Getter@Setter
	private int order_final_sale_price;
	
	public void getOrderPriceInfo() {
		// 상품 비용 & 적립포인트
			for(OrderItemDTO order : orders) {
				order_sale_price += order.getTotal_price();
				order_save_point += order.getTotal_save_point();
			}
		// 배송비용 
			if(order_sale_price >= 30000) {
				delivery_cost = 0;
			} else {
				delivery_cost = 3000;
			}
		// 최종 비용(상품 비용 + 배송비 - 사용 포인트)
			order_final_sale_price = order_sale_price + delivery_cost - use_point;
	}
}

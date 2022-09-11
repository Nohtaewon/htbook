package com.ht.order.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class OrderItemDTO {
	@Getter @Setter
	private String orderId;
	@Getter @Setter
    private int bookId;
	@Getter @Setter
    private int book_count;
	@Getter @Setter
    private int order_item_id;
	@Getter @Setter
    private int bookPrice;
	@Getter @Setter
    private double bookDiscount;
	@Getter @Setter
    private int save_point;
    
	// DB테이블 존재 하지 않는 데이터
	@Getter @Setter
    private int sale_price;
	@Getter @Setter
    private int total_price;
	@Getter @Setter
    private int total_save_point;
	
	public void initSaleTotal() {
		this.sale_price = (int) (this.bookPrice * (1-this.bookDiscount));
		this.total_price = this.sale_price*this.book_count;
		this.save_point = (int)(Math.floor(this.sale_price*0.05));
		this.total_save_point =this.save_point * this.book_count;
	}
}

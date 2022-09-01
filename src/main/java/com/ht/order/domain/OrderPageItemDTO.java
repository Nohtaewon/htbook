package com.ht.order.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class OrderPageItemDTO {

	//뷰로부터 전달받을 값
	@Getter@Setter
	private int bookId;
	@Getter@Setter
	private int book_count;
	
	// DB로 부터 빋을 값
	@Getter@Setter
	private String bookName;
	@Getter@Setter
	private int bookPrice;
	@Getter@Setter
	private int bookDiscount;
	
	// 만들어 놓을 값
	@Getter@Setter
	private int sale_price; //상품 가격
	@Getter@Setter
	private int total_price; // 총 가격
	@Getter@Setter
	private int point; // 한개의 상품 구매로 받을 수 있는 포인트
	@Getter@Setter
	private int total_point; // 총 포인트
	
	public void initSaleTotal() {
		this.sale_price = (int) (this.bookPrice * (1-this.bookDiscount));
		this.total_price = this.sale_price*this.book_count;
		this.point = (int)(Math.floor(this.sale_price*0.05));
		this.total_point =this.point * this.book_count;
	}
	
}

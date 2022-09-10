package com.ht.cart.domain;

import java.util.List;

import com.ht.book.domain.AttachImageVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 장바구니 데이터를 전달, 받을 수 있도록 도와줄 DTO
@ToString
public class CartDTO {
	@Getter @Setter
	private int cart_id;
	@Getter @Setter
	private String member_id;
	@Getter @Setter
	private int bookId;
	@Getter @Setter
	private int book_count;


	//book , join 하기 위해
	@Getter @Setter
	private String bookName;
	@Getter @Setter
	private int bookPrice;
	@Getter @Setter
	private double bookDiscount;
	
	// 추가, 할인율 적용/ 수량을 곱한 총 가격
	// Setter 메서드 추가 X =  initSaleTotal 
	// 메서드를 통해서만 가능하도록 하기 위함
	@Getter
	private int sale_price;
	@Getter
	private int total_price;
	
	// pont
	@Getter
	private int point; // 상품 한개의 받을 수 있는 포인트
	@Getter
	private int total_point; // 수량을 곱한 총 포인트
	
	//상품 이미지
	@Getter @Setter
	private List<AttachImageVO> imageList;
	
	
	public void initSaleTotal() {
		this.sale_price = (int)(this.bookPrice * (1- this.bookDiscount));
		this.total_price = this.sale_price * this.book_count;
		this.point = (int)(Math.floor(this.sale_price*0.05));
		this.total_point = this.point * this.book_count;
	}
	

	
}

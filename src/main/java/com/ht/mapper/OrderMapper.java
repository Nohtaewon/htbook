package com.ht.mapper;

import com.ht.order.domain.OrderPageItemDTO;

public interface OrderMapper {
	
	// 주문 상품 정보
	public OrderPageItemDTO getGoodsInfo(int bookId);
}

package com.ht.order.service;

import java.util.List;

import com.ht.order.domain.OrderPageItemDTO;

public interface OrderService {
	
	// 주문정보
	public List<OrderPageItemDTO> getGoodInfo(List<OrderPageItemDTO> orders);
}
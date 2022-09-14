package com.ht.order.service;

import java.util.List;

import com.ht.common.Criteria;
import com.ht.order.domain.OrderCancelDTO;
import com.ht.order.domain.OrderDTO;
import com.ht.order.domain.OrderPageItemDTO;

public interface OrderService {
	
	// 주문정보
	public List<OrderPageItemDTO> getGoodInfo(List<OrderPageItemDTO> orders);
	
	// 주문
	public void order(OrderDTO ord); 
	
	// 주문 취소
	public void orderCancle(OrderCancelDTO dto);
	
	// 주문 상품 리스트
	public List<OrderDTO> getOrderList(Criteria cri);
	
	// 주문 총 갯수
	public int getOrderTotal(Criteria cri);
}

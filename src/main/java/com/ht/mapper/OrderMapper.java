package com.ht.mapper;

import java.util.List;

import com.ht.book.domain.BookVO;
import com.ht.cart.domain.CartDTO;
import com.ht.common.Criteria;
import com.ht.member.domain.MemberVO;
import com.ht.order.domain.OrderDTO;
import com.ht.order.domain.OrderItemDTO;
import com.ht.order.domain.OrderPageItemDTO;

public interface OrderMapper {
	
	// 주문 상품 정보(페이지)
	public OrderPageItemDTO getGoodsInfo(int bookId);
	
	// 주문 상품 정보 (처리)
	public OrderItemDTO getOrderInfo(int bookId);
	
	// 주문 테이블 등록
	public int enrollOrder(OrderDTO ord); 
	
	// 주문 아이템 테이블 등록
	public int enrollOrderItem(OrderItemDTO orid);
	
	// 주문 금액 차감
	public int deductMoney(MemberVO member);
	
	// 주문 재고 차감
	public int deductStock(BookVO book);
	
	// 카트 제거(주문)
	public int deleteOrderCart(CartDTO dto);
	
	// 주문 취소
	public int orderCancle(String orderId);
	
	// 주문 상품 OrderItem 정보(주문취소)
	public List<OrderItemDTO> getOrderItemInfo(String orderId);
	
	// 주문 ORDER 정보(주문취소)
	public OrderDTO getOrder(String orderId);
	
	// 주문 상품 리스트
	public List<OrderDTO> getOrderList(Criteria cri);
		
	// 주문 총 갯수
	public int getOrderTotal(Criteria cri);
	
}

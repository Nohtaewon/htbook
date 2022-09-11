package com.ht.order.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ht.book.domain.AttachImageVO;
import com.ht.book.domain.BookVO;
import com.ht.cart.domain.CartDTO;
import com.ht.mapper.AttachMapper;
import com.ht.mapper.BookMapper;
import com.ht.mapper.CartMapper;
import com.ht.mapper.MemberMapper;
import com.ht.mapper.OrderMapper;
import com.ht.mapper.SearchMapper;
import com.ht.member.domain.MemberVO;
import com.ht.order.domain.OrderDTO;
import com.ht.order.domain.OrderItemDTO;
import com.ht.order.domain.OrderPageItemDTO;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private SearchMapper searchMapper;
	
	@Override
	public List<OrderPageItemDTO> getGoodInfo(List<OrderPageItemDTO> orders) {
		List<OrderPageItemDTO> result = new ArrayList<OrderPageItemDTO>();
		
		for(OrderPageItemDTO ord : orders) {
			OrderPageItemDTO goodsInfo = orderMapper.getGoodsInfo(ord.getBookId());
			
			goodsInfo.setBook_count(ord.getBook_count());// 뷰로부터 전달받을 book_count값 대입
			
			goodsInfo.initSaleTotal(); // 할인된 가격, 총가격, 포인트, 구매시 받는 총 포인트

			
			List<AttachImageVO> imageList = attachMapper.getAttachList(goodsInfo.getBookId());
			
			goodsInfo.setImageList(imageList);
			
			result.add(goodsInfo);
		}
		return result;
	}

	@Override
	@Transactional // 하나의 단위로 처리
	public void order(OrderDTO ord) {
		// 사용할 데이터
		
		// 회원 정보
		MemberVO member = memberMapper.getMemberInfo(ord.getMember_id());
		
		// 주문 정보
		List<OrderItemDTO> ords = new ArrayList<>();
		for(OrderItemDTO oit: ord.getOrders()) {
			OrderItemDTO orderItem = orderMapper.getOrderInfo(oit.getBookId());
			
			// 수량
			orderItem.setBook_count(oit.getBook_count());
			
			// 기본정보
			orderItem.initSaleTotal();
			
			// List객체 츠가
			ords.add(orderItem);
						
		}
		
		// OrderDTO 
		ord.setOrders(ords);
		ord.getOrderPriceInfo();
		
		// DB 주문, 주문상품, 배송정보
		
		// orderId: "회원 아이디" + "_년도 월 일 분" 형태 만들기
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
		String orderId = member.getMember_id() + format.format(date);
		ord.setOrder_id(orderId);
		
		// DB 등록
		orderMapper.enrollOrder(ord); // order 테이블 등록
		
		// orderItem 등록
		for(OrderItemDTO oit : ord.getOrders()) {		
			oit.setOrder_id(orderId);
			orderMapper.enrollOrderItem(oit);			
		}
		
		// 비용 포인트 변동 적용
		
		// 비용차감, 돈 변동
		int calMoney = member.getMoney();
		calMoney -= ord.getOrder_sale_price();
		member.setMoney(calMoney);
		
		// 포인트 차감, 포인트 변동
		int calPoint = member.getPoint();
		calPoint = calPoint - ord.getUse_point() + ord.getOrder_save_point();	// 기존 포인트 - 사용 포인트 + 획득 포인트
		member.setPoint(calPoint);
		
		// 변동된 돈, 포인트 DB 적용
		orderMapper.deductMoney(member);
		
		// 재고 변동 
		for(OrderItemDTO oit : ord.getOrders()) {
			// 변동 재고 값
			BookVO book = searchMapper.getGoodsInfo(oit.getBookId());
			book.setBookStock(book.getBookStock() - oit.getBook_count());
			// DB 적용
			orderMapper.deductStock(book);
		}
		
		// 장바구니 제거
		for(OrderItemDTO oit : ord.getOrders()) {
			CartDTO dto = new CartDTO();
			dto.setMember_id(ord.getMember_id());
			dto.setBookId(oit.getBookId());
			
			cartMapper.deleteOrderCart(dto);
		}
	}

}

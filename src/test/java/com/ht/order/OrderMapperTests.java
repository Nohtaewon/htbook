package com.ht.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.cart.domain.CartDTO;
import com.ht.config.RootConfig;
import com.ht.mapper.OrderMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
public class OrderMapperTests {
	@Autowired
	private OrderMapper mapper;
	
	// 상품 정보(주문 처리)
//	@Test
//	public void getOrderInfoTest() {
//		
//		 OrderItemDTO orderInfo = mapper.getOrderInfo(1);
//		 
//		 System.out.println("result : " + orderInfo);
//	}
	
	// Order 테이블 등록
//	@Test
//	public void enrollOrderTest() {
//		
//		OrderDTO ord = new OrderDTO();
//		List<OrderItemDTO> orders = new ArrayList();
//		
//		OrderItemDTO order1 = new OrderItemDTO();
//		
//		order1.setBookId(1);
//		order1.setBook_count(5);
//		order1.setBookPrice(70000);
//		order1.setBookDiscount(0.1);
//		order1.initSaleTotal();
//		
//		
//		
//		ord.setOrders(orders);
//		
//		ord.setOrder_id("2021_test1");
//		ord.setAddressee("test");
//		ord.setMember_id("admin");
//		ord.setMember_addr1("test");
//		ord.setMember_addr2("test");
//		ord.setMember_addr3("test");
//		ord.setOrder_state("배송중비");
//		ord.getOrderPriceInfo();
//		ord.setUse_point(1000);
//		
//		mapper.enrollOrder(ord);
//		
//		System.out.println("result : " + ord);
//		
//	}
	
	// orderItem 테이블 등록
//	@Test
//	public void enrollOrderItemTest() {
//		
//		OrderItemDTO oid = new OrderItemDTO();
//		
//		oid.setOrder_id("2021_test1");
//		oid.setBookId(1);
//		oid.setBook_count(1);
//		oid.setBookPrice(70000);
//		oid.setBookDiscount(0.1);
//				
//		oid.initSaleTotal();
//		
//		mapper.enrollOrderItem(oid);
//		
//		System.out.println("result : " + oid);
//		
//	}	
	
	// 회원 돈, 포인트 정보 변경
//	@Test
//	public void deductMoneyTest() {
//		
//		MemberVO member = new MemberVO();
//		
//		member.setMember_id("admin");
//		member.setMoney(500000);
//		member.setPoint(10000);
//		
//		mapper.deductMoney(member);
//		System.out.println("result : " + member);
//	}
	
	//상품 재고 변경
//	@Test
//	public void deductStockTest() {
//		BookVO book = new BookVO();
//		
//		book.setBookId(1);
//		book.setBookStock(77);
//		
//		mapper.deductStock(book);
//		
//		System.out.println("result : " + book);
//	}
	
	//장바구니 제거(주문처리)
//	@Test
//	public void deleteOrderCart() {
//		String memberId = "admin";
//		int bookId = 3201;
//		
//		CartDTO dto = new CartDTO();
//		dto.setMember_id(memberId);
//		dto.setBookId(bookId);
//		
//		mapper.deleteOrderCart(dto);
//		System.out.println("result : " + dto);
//		
//	}
}

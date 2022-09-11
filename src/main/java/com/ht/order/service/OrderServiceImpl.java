package com.ht.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.book.domain.AttachImageVO;
import com.ht.mapper.AttachMapper;
import com.ht.mapper.OrderMapper;
import com.ht.order.domain.OrderPageItemDTO;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
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

}

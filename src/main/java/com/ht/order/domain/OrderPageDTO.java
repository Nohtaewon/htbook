package com.ht.order.domain;

import java.util.List;

import lombok.Data;

@Data
public class OrderPageDTO {
	
	private List<OrderPageItemDTO> orders;
}

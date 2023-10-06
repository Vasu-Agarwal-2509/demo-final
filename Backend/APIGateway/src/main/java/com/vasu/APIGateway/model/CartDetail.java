package com.vasu.APIGateway.model;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail {
	
	private String customerId;
	private String itemId;
	private int quantity;
	private HashMap<String, Integer> productDetail;
	private double cartTotal;
}

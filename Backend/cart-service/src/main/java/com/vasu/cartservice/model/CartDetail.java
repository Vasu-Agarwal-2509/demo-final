package com.vasu.cartservice.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail {
	@Id
	private String customerId;
	private String itemId;
	private int quantity;
	private HashMap<String, Integer> productDetail;
	private double cartTotal;
}

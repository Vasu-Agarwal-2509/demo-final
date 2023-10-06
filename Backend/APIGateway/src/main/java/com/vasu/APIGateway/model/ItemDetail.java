package com.vasu.APIGateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetail {
	
	private String itemId;
	private String itemName;	
	private double itemPrice;
	private String couponCode;
	private int discountOnCouponCode;
	private byte[] image;
	private int rating;
	private String description;
	private int inStock;
	private int discount;
	
}

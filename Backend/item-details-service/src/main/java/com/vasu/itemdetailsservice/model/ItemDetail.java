package com.vasu.itemdetailsservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetail {
	
	@Id
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
	private String category;
	
}

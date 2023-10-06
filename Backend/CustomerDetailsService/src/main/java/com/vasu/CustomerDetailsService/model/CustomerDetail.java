package com.vasu.CustomerDetailsService.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetail {
	
	@Id
	private String emailId;
	private String name;
	private String phoneNumber;		
	private String address;			
	private List<String> preferences;
	private double totalAmount;
	private List<String> coupons;
}

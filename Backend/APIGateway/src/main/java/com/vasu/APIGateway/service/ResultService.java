package com.vasu.APIGateway.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vasu.APIGateway.model.ItemDetail;

@Service 
public class ResultService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<ItemDetail> getProductFromId(String customerId) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		List<ItemDetail> list = new ArrayList<ItemDetail>();
		hm = restTemplate.getForObject("http://localhost:8084/cartService/getProductFromCart/" + customerId, HashMap.class);
		for(String itemId : hm.keySet()) {
			ItemDetail itemObj = restTemplate.getForObject("http://localhost:8083/itemService/getProductById/" + itemId, ItemDetail.class);
			list.add(itemObj);
		}
		return list;
	}
	
	public double findCartTotal(String customerId) {
		double cartTotal = 0;
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		hm = restTemplate.getForObject("http://localhost:8084/cartService/getProductFromCart/" + customerId, HashMap.class);
		for(String itemId : hm.keySet()) {
			ItemDetail itemObj = restTemplate.getForObject("http://localhost:8083/itemService/getProductById/" + itemId, ItemDetail.class);
			cartTotal += itemObj.getItemPrice() * hm.get(itemId);
		}
		
		return cartTotal;
	}
}

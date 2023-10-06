package com.vasu.cartservice.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vasu.cartservice.model.CartDetail;
import com.vasu.cartservice.repository.CartRepo;

@Service
public class CartService {
	
	@Autowired
	private CartRepo cartRepo;
	
	public void addCustomer(String customerId) {
		CartDetail cartObj = new CartDetail();
		cartObj.setCustomerId(customerId);
		cartObj.setProductDetail(new HashMap<String, Integer>());
		cartRepo.save(cartObj);
	}
	
	public void addProductInCart(CartDetail cartDetail) {
		
		Optional<CartDetail> obj = cartRepo.findById(cartDetail.getCustomerId());
		
		if(obj.isEmpty() || obj.get().getProductDetail() == null) {
			HashMap<String, Integer> hm = new HashMap<>();
			hm.put(cartDetail.getItemId(), 1);
			cartDetail.setProductDetail(hm);
			cartRepo.save(cartDetail);
		}
		else {
			HashMap<String, Integer> hm = obj.get().getProductDetail();
			if(hm.containsKey(cartDetail.getItemId())) {
				int val = obj.get().getProductDetail().get(cartDetail.getItemId()) + 1;
				hm.put(cartDetail.getItemId(), val);
			}
			else {
				hm.put(cartDetail.getItemId(), 1);
			}
			cartDetail.setProductDetail(hm);
			cartRepo.save(cartDetail);
		}
	}
	
	public HashMap<String, Integer> getProductFromCart(String customerId){
		return cartRepo.findById(customerId).get().getProductDetail();
	}
	
	public void removeProductFromCart(String customerId, String itemId) {
		CartDetail obj = cartRepo.findById(customerId).get();
		HashMap<String,Integer> hm = obj.getProductDetail(); 
		if(hm.get(itemId) == 1) {
			hm.remove(itemId);
		}
		else if(hm.get(itemId) > 1) {
			hm.put(itemId, hm.get(itemId) - 1);
		}
		obj.setProductDetail(hm);
		cartRepo.save(obj);
	}
}

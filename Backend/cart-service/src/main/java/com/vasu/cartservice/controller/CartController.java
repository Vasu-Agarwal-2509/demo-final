package com.vasu.cartservice.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vasu.cartservice.model.CartDetail;
import com.vasu.cartservice.service.CartService;

@CrossOrigin("http://localhost:4200/")
@RequestMapping("/cartService")
@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	
	
	@PostMapping("/addProductInCart")
	public String addProductInCart(@RequestBody CartDetail cartDetail) {
		cartService.addProductInCart(cartDetail);
		return "Product added successfully in cart";
	}
	
	@GetMapping("/addCustomerInCart/{customerId}")
	public void addCustomer(@PathVariable String customerId) {
		cartService.addCustomer(customerId);
	}
	
	@GetMapping("/getProductFromCart/{customerId}")
	public HashMap<String, Integer> getProductFromCart(@PathVariable String customerId){
		return cartService.getProductFromCart(customerId);
	}
	
	@GetMapping("/removeProductFromCart/{customerId}/{itemId}")
	public void removeProductFromCart(@PathVariable String customerId , @PathVariable String itemId) {
		cartService.removeProductFromCart(customerId, itemId);
	}
}

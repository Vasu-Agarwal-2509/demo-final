package com.vasu.itemdetailsservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vasu.itemdetailsservice.model.ItemDetail;
import com.vasu.itemdetailsservice.service.ItemService;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/itemService")
public class ItemsController {
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/addProducts")
	public String addProducts(@RequestParam("file") MultipartFile file,
			@RequestParam("itemId") String itemId,
			@RequestParam("itemName") String itemName,
			@RequestParam("itemPrice") double itemPrice,
			@RequestParam("couponCode") String couponCode,
			@RequestParam("discountOnCouponCode") int discountOnCouponCode,
			@RequestParam("rating") int rating,
			@RequestParam("description") String description,
			@RequestParam("inStock") int inStock,
			@RequestParam("discount") int discount,
			@RequestParam("category") String category) throws IOException{
		
		
		return itemService.addProducts(file, itemId, itemName, itemPrice, couponCode, discountOnCouponCode, rating, description, inStock, discount, category);
	}
	
	@GetMapping("/getProducts")
	public List<ItemDetail> getProducts() {
		return itemService.getProducts();
	}
	
	@GetMapping("/getProductById/{itemId}")
	public ItemDetail getProductById(@PathVariable String itemId) {
		return itemService.getProductById(itemId);
	}
	
	@GetMapping("/getDiscountOnCouponCode/{couponCode}")
	public int getDiscountOnCouponCode(@PathVariable String couponCode) {
		return itemService.getDiscountOnCouponCode(couponCode);
	}
	
	@PutMapping("/updateProductPrice/{itemId}")
	public ItemDetail updateProductPrice(@RequestParam("itemPrice") double itemPrice, @PathVariable String itemId) {
		return itemService.updateProductDetail(itemPrice, itemId);
	}
	
	@PutMapping("/updateCouponCodeDiscount/{itemId}")
	public ItemDetail updateCouponCodeDiscount(@RequestParam("discountOnCouponCode") int discountOnCouponCode, @PathVariable String itemId) {
		return itemService.updateCouponCodeDiscount(discountOnCouponCode, itemId);
	}
	
	@PutMapping("/updateInStock/{itemId}")
	public ItemDetail updateInStock(@RequestParam("inStock") int inStock, @PathVariable String itemId) {
		return itemService.updateInStock(inStock, itemId);
	}
	
	@PutMapping("/updateDiscount/{itemId}")
	public ItemDetail updateDiscount(@RequestParam("discount") int discount, @PathVariable String itemId) {
		return itemService.updateDiscount(discount, itemId);
	}
	
	@DeleteMapping("/deleteProduct/{itemId}")
	public void deleteProduct(@PathVariable String itemId) {
		itemService.deleteProduct(itemId);
	}
}

package com.vasu.itemdetailsservice.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vasu.itemdetailsservice.model.ItemDetail;
import com.vasu.itemdetailsservice.repository.ItemRepo;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepo itemRepo;
	
	public String addProducts(MultipartFile file, String itemId, String itemName, double itemPrice, String couponCode, int discountOnCouponCode, int rating, String description, int inStock, int discount, String category) throws IOException{
		ItemDetail itemDetail = new ItemDetail(itemId, itemName, itemPrice, couponCode, discountOnCouponCode, file.getBytes(),rating, description, inStock, discount, category);	
		itemRepo.save(itemDetail);
		return "Image saved in DB";
	}
	
	public List<ItemDetail> getProducts() {
		return itemRepo.findAll();
	}
	
	public ItemDetail getProductById(String itemId) {
		return itemRepo.findById(itemId).get();
	}
	
	public int getDiscountOnCouponCode(String couponCode) {
		return itemRepo.findByCouponCode(couponCode).getDiscountOnCouponCode();
	}
	
	public ItemDetail updateProductDetail(double itemPrice, String itemId) {
		Optional<ItemDetail> obj = itemRepo.findById(itemId);
		obj.get().setItemPrice(itemPrice);
		return itemRepo.save(obj.get());
	}
	
	public ItemDetail updateCouponCodeDiscount(int discountOnCouponCode, String itemId) {
		Optional<ItemDetail> obj = itemRepo.findById(itemId);
		obj.get().setDiscountOnCouponCode(discountOnCouponCode);
		return itemRepo.save(obj.get());
	}
	
	public ItemDetail updateInStock(int inStock, String itemId) {
		Optional<ItemDetail> obj = itemRepo.findById(itemId);
		obj.get().setInStock(inStock);
		return itemRepo.save(obj.get());
	}
	
	public ItemDetail updateDiscount(int discount, String itemId) {
		Optional<ItemDetail> obj = itemRepo.findById(itemId);
		obj.get().setDiscount(discount);
		return itemRepo.save(obj.get());
	}
	
	public void deleteProduct(String itemId) {
		itemRepo.deleteById(itemId);
	}
}

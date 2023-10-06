package com.vasu.itemdetailsservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vasu.itemdetailsservice.model.ItemDetail;

@Repository
public interface ItemRepo extends MongoRepository<ItemDetail, String>{
	public ItemDetail findByCouponCode(String couponCode);
}

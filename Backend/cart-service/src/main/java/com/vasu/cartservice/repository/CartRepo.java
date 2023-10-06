package com.vasu.cartservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vasu.cartservice.model.CartDetail;

@Repository
public interface CartRepo extends MongoRepository<CartDetail, String>{
	
}

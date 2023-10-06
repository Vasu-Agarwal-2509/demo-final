package com.vasu.CustomerDetailsService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vasu.CustomerDetailsService.model.CustomerDetail;

@Repository
public interface CustomerRepo extends MongoRepository<CustomerDetail, String>{

}

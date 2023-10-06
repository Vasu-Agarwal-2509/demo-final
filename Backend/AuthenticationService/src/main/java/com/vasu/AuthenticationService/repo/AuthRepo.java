package com.vasu.AuthenticationService.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vasu.AuthenticationService.model.AuthDetails;

@Repository
public interface AuthRepo extends MongoRepository<AuthDetails, String>{

}

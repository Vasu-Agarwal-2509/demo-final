package com.vasu.AuthenticationService.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vasu.AuthenticationService.model.ConfirmationToken;

@Repository
public interface TokenRepo extends MongoRepository<ConfirmationToken, String>{

}

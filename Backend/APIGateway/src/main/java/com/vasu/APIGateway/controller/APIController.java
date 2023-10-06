package com.vasu.APIGateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vasu.APIGateway.model.ItemDetail;
import com.vasu.APIGateway.service.ResultService;

@CrossOrigin("http://localhost:4200")
@RequestMapping("/apigatewayService")
@RestController
public class APIController {
	
	@Autowired
	private ResultService resultService;
	
	@GetMapping("/getProductFromId/{customerId}")
	public List<ItemDetail> getProductFromId(@PathVariable String customerId) {
		return resultService.getProductFromId(customerId);
	}
	
	@GetMapping("/findCartTotal/{customerId}")
	public double findCartTotal(@PathVariable String customerId) {
		return resultService.findCartTotal(customerId);
	}
}

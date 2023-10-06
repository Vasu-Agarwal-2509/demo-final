package com.vasu.CustomerDetailsService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vasu.CustomerDetailsService.model.CustomerDetail;
import com.vasu.CustomerDetailsService.service.CustomerService;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/customerService")
public class CustomerController {
	
	@Autowired
	private CustomerService custService;
	
	// Add customer details
	@PostMapping("/addCustomer")
	public boolean addCustomerDetail(@RequestBody CustomerDetail customerObj) {
		return custService.addDetail(customerObj);
	}
	
	// Get customer details
	@GetMapping("/getCustomer/{emailid}")
	public CustomerDetail getCustomerDetail(@PathVariable String emailid){
		return custService.getDetails(emailid);
	}
	
	//	Add coupon in list by emailId
	@GetMapping("/addCoupon/{emailid}/{couponCode}")
	public void addCoupon(@PathVariable String emailid, @PathVariable String couponCode) {
		custService.addCoupon(emailid, couponCode);
	}
	
	@GetMapping("/deleteCoupon/{emailid}/{couponCode}")
	public void deleteCoupon(@PathVariable String emailid, @PathVariable String couponCode) {
		custService.deleteCoupon(emailid, couponCode);
	}
	
	@GetMapping("/updatePrice/{emailid}/{totalAmount}")
	public void updatePrice(@PathVariable String emailid, @PathVariable double totalAmount) {
		custService.updatePrice(emailid, totalAmount);
	}
	
	// Get all coupons on that emailId
	@GetMapping("/getAllCoupons/{emailid}")
	public List<String> getAllCoupons(@PathVariable String emailid){
		return custService.getAllCoupons(emailid);
	}
	
	// Delete customer
	@DeleteMapping("/deleteCustomer/{emailid}")
	public void deleteCustomer(@PathVariable String emailid) {
		custService.deleteCustomer(emailid);
	}
}




//@PutMapping("/updateCustomerDetails")
//public void updateCustomerDetails(@RequestBody CustomerDetail customerDetail) {
//	custService.updateCustomerDetails(customerDetail);
//}
//
//// Update Phone Number
//@PutMapping("/updatePhoneNumber")
//public void updatePhoneNumber(@RequestBody CustomerDetail customerDetail) {
//	custService.updateByPhoneNumber(customerDetail);
//}
//
//// Update Address
//@PutMapping("/updateAddress")
//public void updateAddress(@RequestBody CustomerDetail customerDetail) {
//	custService.updateAddress(customerDetail);
//}
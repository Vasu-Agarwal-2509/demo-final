package com.vasu.AuthenticationService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vasu.AuthenticationService.jwt.JwtService;
import com.vasu.AuthenticationService.model.AuthDetails;
import com.vasu.AuthenticationService.model.ConfirmationToken;
import com.vasu.AuthenticationService.service.AuthService;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/authService")
public class AuthController {
	
	@Autowired	
	private AuthService authService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	// For registration of user
	@PostMapping("/sendVerificationCode")
	public boolean sendVerificationCode(@RequestBody AuthDetails authDetail) {
		return authService.sendVerificationCode(authDetail);
	}
	
	@GetMapping("/verifyAccount/{token}")
	public boolean verifyAccount(@PathVariable("token") String confirmationToken) {
		return authService.verifyAccount(confirmationToken);
	}
	
	// For login
	// @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@PostMapping("/validate")
	public boolean validateUserDetail(@RequestBody AuthDetails authDetail) {
		return authService.validateUserDetail(authDetail);
	}
	
	// For getting all user details
    // @PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/getAuthDetails")
	public List<AuthDetails> getDetails() {
		return authService.getDetails();
	}
	
	// For updating password
	// @PreAuthorize("hasAuthority('USER')")
	@PutMapping("/editAuthDetail")
	public String editDetail(@RequestBody AuthDetails authDetail) {
		if(authService.editDetail(authDetail)) {
			return "Password updated successfully";
		}
		return "Enter valid username";
	}
	
	// For deleting user
	//	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/deleteAuthDetail/{username}")
	public String deleteDetail(@PathVariable String username) {
		if(authService.deleteDetail(username)) {
			return "Deleted Successfully";
		}
		return "Enter valid username";
	}
	
	@PostMapping("/generatetoken")
	public AuthDetails authenticateAndGetToken(@RequestBody AuthDetails authDetail){ 
		//for token creation
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDetail.getEmailid(), authDetail.getPassword()));
		if(authentication.isAuthenticated()) {
//			return jwtService.generateToken(authDetail.getEmailid());
			authDetail.setToken(jwtService.generateToken(authDetail.getEmailid()));
			return authDetail;
			
		}
		else {
			throw new UsernameNotFoundException("invalid user request!");
		}
	}
	
	@PostMapping("/validateToken")
	public String validateToken(@RequestParam String token) {
		jwtService.validateByToken(token);
		return "Token is valid";
	}
	
	@GetMapping("/getRoles/{token}")
	public List<String> getRoles(@PathVariable String token){
		return jwtService.getRoles(token);
	}
}
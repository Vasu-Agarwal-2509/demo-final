package com.vasu.AuthenticationService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vasu.AuthenticationService.model.AuthDetails;
import com.vasu.AuthenticationService.model.ConfirmationToken;
import com.vasu.AuthenticationService.repo.AuthRepo;
import com.vasu.AuthenticationService.repo.TokenRepo;

@Service
public class AuthService {
	
	@Autowired
	private AuthRepo authRepo;
	
	@Autowired
	private TokenRepo tokenRepo;
	
	@Autowired
	private EmailService emailService;
	
	public boolean sendVerificationCode(AuthDetails authDetail) {
		Optional<AuthDetails> obj = authRepo.findById(authDetail.getEmailid());
		if(!obj.isEmpty()) {
			return false;
		}
		else {
//			authRepo.save(authDetail);
			ConfirmationToken confirmationToken  = new ConfirmationToken(authDetail);
			tokenRepo.save(confirmationToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(authDetail.getEmailid());
			mailMessage.setSubject("Complete Registration");
			mailMessage.setText("To confirm your account, enter this code : " +
								confirmationToken.getConfirmationToken());
			emailService.sendMail(mailMessage);
			return true;
		}
		
	}
	
	public boolean verifyAccount(String confirmationToken) {
		Optional<ConfirmationToken> obj = tokenRepo.findById(confirmationToken);
		if(!obj.isEmpty()) {
			ConfirmationToken token = obj.get();
			authRepo.save(token.getAuthDetails());
//			tokenRepo.deleteById(confirmationToken);
			return true;
		}
		return false;
	}
	
	public boolean validateUserDetail(AuthDetails authDetail) {
		Optional<AuthDetails> obj = authRepo.findById(authDetail.getEmailid());
		if(obj.isEmpty()) {
			return false;
		}
		
		if(!obj.get().getPassword().equals(authDetail.getPassword())) {
			return false;
		}
		return true;
	}
	
	public List<AuthDetails> getDetails() {
		return authRepo.findAll();
	}
	
	public boolean editDetail(AuthDetails authDetail) {
		Optional<AuthDetails> obj = authRepo.findById(authDetail.getEmailid()); 
		if(obj.isEmpty()) {
			return false;
		}
		authRepo.save(authDetail);
		return true;
	}
	
	public boolean deleteDetail(String username) {
		if(authRepo.findById(username).isEmpty()) {
			return false;
		}
		authRepo.deleteById(username);
		return true;
	}
}


//"http://localhost:8081/authService/confirmAccount?token=" +
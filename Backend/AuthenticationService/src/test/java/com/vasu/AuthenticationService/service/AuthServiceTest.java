package com.vasu.AuthenticationService.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.vasu.AuthenticationService.model.AuthDetails;
import com.vasu.AuthenticationService.repo.AuthRepo;

@SpringBootTest
public class AuthServiceTest {
	
	@Mock
	private AuthRepo authRepo;
	
	@InjectMocks
	private AuthService authService;
	
	@Test
	void addDetail() {
		AuthDetails authDetailObj = new AuthDetails("vasu", "vasu12345", "USER", "");
		
		authService.sendVerificationCode(authDetailObj);
		verify(authRepo).findById("vasu");
		
		Optional<AuthDetails> obj = Optional.of(authDetailObj);
		when(authRepo.findById("vasu")).thenReturn(obj);
		assertFalse(authService.sendVerificationCode(authDetailObj));
	}
	
	@Test
	void getDetails() {
		authService.getDetails();
		verify(authRepo).findAll();
	}
	
	@Test
	void editDetail() {
		AuthDetails authDetailObj = new AuthDetails("vasu", "vasu12345", "USER", "");
		
		authService.editDetail(authDetailObj);
		verify(authRepo).findById("vasu");
		
		Optional<AuthDetails> obj = Optional.empty();
		when(authRepo.findById("vasu")).thenReturn(obj);
		assertFalse(authService.editDetail(authDetailObj));
	}
	
	@Test
	void deleteDetail() {
		
		authService.deleteDetail("vasu");
		verify(authRepo).findById("vasu");
		
		Optional<AuthDetails> obj = Optional.empty();
		
		when(authRepo.findById("vasu")).thenReturn(obj);
		assertFalse(authService.deleteDetail("vasu"));
	}
	
}

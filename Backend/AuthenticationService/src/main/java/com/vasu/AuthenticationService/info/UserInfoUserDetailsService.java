package com.vasu.AuthenticationService.info;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.vasu.AuthenticationService.model.AuthDetails;
import com.vasu.AuthenticationService.repo.AuthRepo;

@Component
public class UserInfoUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AuthRepo authRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AuthDetails> authDetail = authRepo.findById(username);
		return authDetail.map(UserInfoUserDetails::new)
						.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}
}

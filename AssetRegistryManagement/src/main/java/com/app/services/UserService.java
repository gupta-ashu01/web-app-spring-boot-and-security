package com.app.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.models.Role;
import com.app.models.User;

import com.app.repositories.RoleRepository;
import com.app.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User save(User user, String action) {
		Role userRole = null;
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (action.equalsIgnoreCase("admin")) {
			userRole = roleRepository.findByRole("ADMIN");
		}
		if (action.equalsIgnoreCase("user")) {
			userRole = roleRepository.findByRole("USER");
		}
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		return userRepository.save(user);
	}

}

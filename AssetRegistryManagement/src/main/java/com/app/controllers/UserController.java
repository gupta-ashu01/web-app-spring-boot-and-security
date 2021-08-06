package com.app.controllers;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.models.Role;
import com.app.models.User;
import com.app.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = { "/", "/login" })
	public String login(Model model) {

		model.addAttribute("user", new User());
		return "login";
	}

	@GetMapping("/signup")
	public String signupScreen(Model model) {

		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/signup")
	public String registerTheUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model, @RequestParam("action") String action) {

		User userExists = userService.findByEmail(user.getEmail());

		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "This User Email already exists!");
		}
		if (bindingResult.hasErrors()) {
			return "signup";
		} else {
			userService.save(user,action);
			model.addAttribute("msg", "User has been registered successfully!");
			model.addAttribute("user", new User());
			return "signup";
		}

	}

	@GetMapping("/home")
	public String home(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		
		User user = userService.findByEmail(auth.getName());

		model.addAttribute("userName", user.getName());

		return "home";
	}

	@GetMapping("/access_denied")
	public String accessDenied(Model model) {
		return "access_denied";
	}
}
